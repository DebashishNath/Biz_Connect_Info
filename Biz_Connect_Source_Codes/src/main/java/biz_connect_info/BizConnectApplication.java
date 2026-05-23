package biz_connect_info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"auth_info", "biz_connect_info"}) // Scan both packages
@EnableJpaRepositories(basePackages = {"auth_info.repository","biz_connect_info.repository"}) // Ensure repositories are detected
@EntityScan(basePackages = {"auth_info.models","biz_connect_info.models"})
public class BizConnectApplication {

	public static void main(String[] args) {
		System.out.println("Inside main()");
		SpringApplication.run(BizConnectApplication.class, args);
	}

}