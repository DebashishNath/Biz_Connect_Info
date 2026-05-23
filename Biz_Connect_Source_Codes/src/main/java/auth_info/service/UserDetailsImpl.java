package auth_info.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import auth_info.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import auth_info.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String fullName;

	private String email;

	private String mobileNo;

	private String is2FAEnabled;

	@JsonIgnore
	private String password;

	@Autowired
	UserRepository userRepository;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String username,String fullName, String email, String password,String mobileNo,
						   String is2FAEnabled,Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.fullName=fullName;
		this.email = email;
		this.password = password;
		this.mobileNo = mobileNo;
		this.is2FAEnabled = is2FAEnabled;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(),
				user.getFullName(),
				user.getEmail(),
				user.getPassword(),
				user.getMobileNo(),
				user.getIs2FAEnabled(),
				authorities);
	}


	public UserDetailsImpl loadUserByUsername(String username)
			throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() ->
						new UsernameNotFoundException(
								"User Not Found with username: " + username
						));

		return UserDetailsImpl.build(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getIs2FAEnabled() {
		return is2FAEnabled;
	}

	public void setIs2FAEnabled(String is2FAEnabled) {
		this.is2FAEnabled = is2FAEnabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}