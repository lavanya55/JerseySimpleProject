package com.book;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * Created by Lavanya Kondragunta on 10/30/2015.
 */
@Provider //Marks an implementation of an extension interface that should be discoverable by JAX-RS runtime during a provider scanning phase.
@PoweredBy
public class BookResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        for(Annotation a:responseContext.getEntityAnnotations()){
            if(a.annotationType() == PoweredBy.class) {
                String value= ((PoweredBy)a).value();
                responseContext.getHeaders().add("X-Powered-By", value);
            }
        }
    }
}
