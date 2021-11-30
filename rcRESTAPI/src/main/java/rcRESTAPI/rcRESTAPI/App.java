package rcRESTAPI.rcRESTAPI;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan(basePackages = {"rcRESTAPI.rcRESTAPI.Service",
		"rcRESTAPI.rcRESTAPI.Controllers",
		"rcRESTAPI.rcRESTAPI.Repository",
		"rcRESTAPI.rcRESTAPI.Entity",
		"rcRESTAPI.rcRESTAPI.Service.PasswordEncodeAndMatch"})

public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public HashMap<String, String> hashMap(){
		return new HashMap<>();
	}
}