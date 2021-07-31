package com.ustg.favourites;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ustg.favourites.jwtFilter.JwtFilter;

@SpringBootApplication
public class FavouritesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouritesApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		UrlBasedCorsConfigurationSource urlconfig = new UrlBasedCorsConfigurationSource();

		CorsConfiguration cconfig = new CorsConfiguration();

		cconfig.setAllowCredentials(true);
		cconfig.addAllowedOrigin("*");
		cconfig.addAllowedMethod("*");
		cconfig.addAllowedHeader("*");

		urlconfig.registerCorsConfiguration("/**", cconfig);

		FilterRegistrationBean fbean = new FilterRegistrationBean(new CorsFilter(urlconfig));

		fbean.setFilter(new JwtFilter());

		fbean.addUrlPatterns("/api/*");

		return fbean;
	}

}
