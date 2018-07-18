package kr.co.miracom.mes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
// @EnableDiscoveryClient
@EnableAspectJAutoProxy
public class MiracomMesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiracomMesApplication.class, args);
	}
}
