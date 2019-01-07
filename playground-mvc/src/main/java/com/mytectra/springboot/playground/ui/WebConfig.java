package com.mytectra.springboot.playground.ui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//context.xml

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter  {
	
	/*<bean id = "viewResolver" class = "org.springframework.web.servlet.view.InternalResourceViewResolver">
	<property name="prefix" value="/WEEB-INF/view/"/>
	<property name="sufix" value=".jsp"/>
	</bean>*/

	//Resolve View - JSP
	/*@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/view/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}*/
	
	//Static Resource Mapping
	
	@Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/jsp/**").addResourceLocations("/WEB-INF/static/");
	  }
	
	//Default config DS -> /**
	 @Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }
}
	