package pos_microservicio_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PosMicroservicioEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosMicroservicioEurekaApplication.class, args);
	}

}
