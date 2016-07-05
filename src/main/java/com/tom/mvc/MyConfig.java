package com.tom.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan
@EnableSwagger2
public class MyConfig extends WebMvcConfigurerAdapter{


    //Configuration for the content negotation strategy: Negotiate by accept headers.
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).favorParameter(false).defaultContentType(MediaType.APPLICATION_JSON).ignoreAcceptHeader(false);
    }

    //Swagger config
    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2);
    }

    //Setting up the view resolvers for use in content negotiation
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

        resolvers.add(jsonViewResolver());
        resolvers.add(jspViewResolver());

        resolver.setViewResolvers(resolvers);

        return resolver;
    }

    //Json view resolver set up
    @Bean
    public ViewResolver jsonViewResolver() {
        return new JsonViewResolver();
    }

    //Jsp view resolver set up
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");
        return bean;
    }


}