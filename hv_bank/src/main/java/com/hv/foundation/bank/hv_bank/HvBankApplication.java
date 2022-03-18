package com.hv.foundation.bank.hv_bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class HvBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(HvBankApplication.class, args);
	}

	@Bean
	public Docket BankApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.hv.foundation.bank.hv_bank")).build();
	}
	
	
// for enabling 'Access Control Allow Origin' which is used for fetching our app data in external javascript app.	
//  @	Bean
//  public WebMvcConfigurer corsConfig() {
//	  return new WebMvcConfigurer() {
//		  @Override
//		  public void addCorsMappings(CorsRegistry registry) {
//			  registry.addMapping("/**").allowedHeaders("*");
//		  }
//	};
//  }

}
