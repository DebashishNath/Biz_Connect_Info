package biz_connect_info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BizConnectApplication {

	public static void main(String[] args) {
		System.out.println("Inside main()");
		SpringApplication.run(BizConnectApplication.class, args);
	}

}