package com.example.validation;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.validation.JAXRSBeanValidationInInterceptor;
import org.apache.cxf.jaxrs.validation.JAXRSBeanValidationOutInterceptor;
import org.apache.cxf.validation.BeanValidationProvider;

@Provider
public class BeanValidationDynamicFeature implements DynamicFeature {
    private final JAXRSBeanValidationInInterceptor inInterceptor;
    private final JAXRSBeanValidationOutInterceptor outInterceptor;
    
    public BeanValidationDynamicFeature(final BeanValidationProvider provider) {
        this.inInterceptor = new JAXRSBeanValidationInInterceptor();
        this.inInterceptor.setProvider(provider);
        
        this.outInterceptor = new JAXRSBeanValidationOutInterceptor();
        this.outInterceptor.setProvider(provider);
    }
    
    @Override
    public void configure(final ResourceInfo resourceInfo, final FeatureContext context) {
        if (resourceInfo.getResourceClass().getAnnotation(EnableBeanValidation.class) != null) {
            context.register(inInterceptor);
            context.register(outInterceptor);
        }
    }
}
