package com.kk.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kk.app.filter.CORSFilter;

@Configuration
@SpringBootApplication
public class ExcelApp {

	public static void main(String[] args) {
     
		SpringApplication.run(ExcelApp.class, args);
		
	}
    //comment
		    @Bean
		    public FilterRegistrationBean shallowEtagHeaderFilter() {
		    	System.out.println("++++++++++++++++++++++++++++++++++ initializing filter");
		        FilterRegistrationBean registration = new FilterRegistrationBean();
		        registration.setFilter(new CORSFilter());
		        return registration;
		    }
		

}
