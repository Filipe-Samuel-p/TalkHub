package filipeProject.example.authenticationJwt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AuthenticationJwtApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		System.out.println(new BCryptPasswordEncoder().encode("123"));


	}
}
