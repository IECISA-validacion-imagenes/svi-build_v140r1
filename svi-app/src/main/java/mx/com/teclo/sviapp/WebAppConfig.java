package mx.com.teclo.sviapp;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebAppConfig {
	@Bean
		public WebMvcConfigurerAdapter forwardToIndex() {
			return new WebMvcConfigurerAdapter() {
				@Override
				public void addViewControllers(ViewControllerRegistry registry) {
					registry.addViewController("/").setViewName("forward:/index.html");
	
				}
			};
		}
}

