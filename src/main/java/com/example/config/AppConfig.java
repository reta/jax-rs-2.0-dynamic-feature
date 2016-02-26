package com.example.config;

import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.validation.ValidationExceptionMapper;
import org.apache.cxf.validation.BeanValidationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.example.rs.JaxRsApiApplication;
import com.example.rs.PeopleRestService;
import com.example.rs.ValidatingPeopleRestService;
import com.example.validation.BeanValidationDynamicFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
public class AppConfig {
    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }
    
    @Bean @DependsOn("cxf")
    public Server jaxRsServer() {
        final JAXRSServerFactoryBean factory = 
            RuntimeDelegate.getInstance().createEndpoint( 
                jaxRsApiApplication(), 
                JAXRSServerFactoryBean.class 
            );
        
        factory.setServiceBean(validatingPeopleRestService());
        factory.setServiceBean(peopleRestService());
        factory.setProvider(new JacksonJsonProvider());
        factory.setProvider(new BeanValidationDynamicFeature(new BeanValidationProvider()));
        factory.setProvider(new ValidationExceptionMapper());
        
        return factory.create();
    }
    
    @Bean 
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }
    
    @Bean 
    public ValidatingPeopleRestService validatingPeopleRestService() {
        return new ValidatingPeopleRestService();
    }
    
    @Bean 
    public PeopleRestService peopleRestService() {
        return new PeopleRestService();
    }
}
