package org.system.airportbaggage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.system.airportbaggage"})
@EnableAutoConfiguration
public class AirportBaggageSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportBaggageSystemApplication.class, args);
	}
}
