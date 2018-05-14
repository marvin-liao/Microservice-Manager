package pl.piomin.microservices.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import com.keyholesoftware.publish.swagger.PublishSwagger;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
@PublishSwagger
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
