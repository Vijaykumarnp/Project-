package com.xworkz.rentalapp.parkingconfiguration;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@ComponentScan("com.xworkz.rentalapp")
@EnableWebMvc //adding this annotation will support   Spring MVCconfiguration from WebMvcConfigurationSupport
public class ParkinConfig  implements WebMvcConfigurer{

	public ParkinConfig() {
		System.out.println(" created Parking config ");
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addViewController("/").setViewName("/start");
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean() {
		
		LocalContainerEntityManagerFactoryBean c = new LocalContainerEntityManagerFactoryBean();
		c.setPersistenceUnitName("parking");
		return c;
		
	}
	
}
