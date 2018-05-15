package org.ustb.MicroServiceMgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MicroServiceMgrApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceMgrApplication.class, args);
	}
}
