package com.pr03101444_spboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.pr03101444_spboot.*")
public class ResolverConfig extends WebMvcConfigurerAdapter {

//    @Bean(name = "dataSource")
//	public DriverManagerDataSource dataSource() {
//	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/dummy");
//	    driverManagerDataSource.setUsername("root");
//	    driverManagerDataSource.setPassword("vagif");
//	    return driverManagerDataSource;
//	}
        
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
    }

    @Bean
    public ResourceBundleViewResolver resourceBundleViewResolver() {
        ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
        resolver.setBasename("views");
        resolver.setOrder(1);
        return resolver;
    }

}
