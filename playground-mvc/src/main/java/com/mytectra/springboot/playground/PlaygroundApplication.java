package com.mytectra.springboot.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

//to start the spring boot application
@SpringBootApplication

//To Load the beans from XML File
@ImportResource(locations= {"classpath:beans.xml"})

@EnableAutoConfiguration

//To load the customized property files
@PropertySources(value= {
		@PropertySource(value = {"classpath:application-beans.properties"}),
		@PropertySource(value = {"classpath:application.properties"})
})


public class PlaygroundApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(PlaygroundApplication.class, args);
	}
}
