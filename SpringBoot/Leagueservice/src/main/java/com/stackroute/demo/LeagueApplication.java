package com.stackroute.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.stackroute.demo.jwtfilter.Jwtfilter;

@SpringBootApplication
public class LeagueApplication {
	
	  @Bean
	    public FilterRegistrationBean<Jwtfilter> jwtFilter() {
	    	UrlBasedCorsConfigurationSource urlconfig=new UrlBasedCorsConfigurationSource();
	 		CorsConfiguration config=new CorsConfiguration();
	 		config.setAllowCredentials(true);
	 		config.addAllowedOrigin("*");
	 		config.addAllowedMethod("*");
	 		config.addAllowedHeader("*");
	 		urlconfig.registerCorsConfiguration("/**", config);
	 		FilterRegistrationBean filterbean = new FilterRegistrationBean(new CorsFilter(urlconfig));
	 		filterbean.setFilter(new Jwtfilter());
	 		filterbean.addUrlPatterns("/api/*");
	 		return filterbean;
	    }
	    

	public static void main(String[] args) {
		SpringApplication.run(LeagueApplication.class, args);
	}

}
