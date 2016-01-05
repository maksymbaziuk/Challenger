package com.challenger.config;

import com.challenger.json.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;

import java.util.List;

/**
 * Created by Maksym_Baziuk on 15.12.2015.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.challenger")
@PropertySource("classpath:application.properties")
@Import({DataSourceConfig.class})
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(env.getRequiredProperty("static.content.pattern")).addResourceLocations("classpath:/web/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(getCustomObjectMapper()));
    }

    @Bean
    public CustomObjectMapper getCustomObjectMapper(){
        return new CustomObjectMapper();
    }

    @Bean
    public ControllerClassNameHandlerMapping getHandlerMapping(){
        return new ControllerClassNameHandlerMapping();
    }

}
