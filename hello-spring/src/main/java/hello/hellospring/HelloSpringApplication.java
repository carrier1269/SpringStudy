package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 어노테이션 - SpringBootApplication은 TomCat이라는 웹서버를 내장하고 있다.
@SpringBootApplication
public class HelloSpringApplication {
	public static void main(String[] args) {

		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
