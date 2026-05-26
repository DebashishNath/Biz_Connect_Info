package auth_info.security.jwt;

import auth_info.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true
)
public class WebSecurityConfig {

	private final UserDetailsServiceImpl userDetailsService;

	@Value("${app.cors.allowed-origins}")
	private List<String> allowedOrigins;

	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/* ---------------- Authentication Manager ---------------- */

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

		AuthenticationManagerBuilder authBuilder =
				http.getSharedObject(AuthenticationManagerBuilder.class);

		authBuilder
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());

		return authBuilder.build();
	}

	/* ---------------- Password Encoder ---------------- */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/* ---------------- Security Filter Chain ---------------- */

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(
								"/api/auth/**",
								"/api/test/**",
								"/api/biz_connect/**"
						).permitAll()
						.anyRequest().authenticated()
				);

		return http.build();
	}

	/* ---------------- CORS Configuration ---------------- */

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		System.out.println("Inside corsConfigurationSource()");
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowedOrigins(allowedOrigins);
		config.setAllowedMethods(
				List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
		);
		config.setAllowedHeaders(List.of("*"));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source =
				new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", config);

		return source;
	}
}