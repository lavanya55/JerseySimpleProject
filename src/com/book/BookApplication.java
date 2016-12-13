package com.book;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.UriConnegFilter;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;

/**
 * Created by Lavanya Kondragunta on 10/30/2015.
 */
public class BookApplication extends ResourceConfig {
    final BookDao bookDao = new BookDao();

    /*Register JAX-RS application components.*/
    public BookApplication() {
        //DEPENDENCY INJECTION
        packages("com.Book").
                register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(bookDao).to(BookDao.class);
                    }
                });

        //BUILT-FILTERS
        HashMap<String, MediaType> mappings = new HashMap<>();
        mappings.put("xml", MediaType.APPLICATION_XML_TYPE);
        mappings.put("json", MediaType.APPLICATION_JSON_TYPE);
        UriConnegFilter uriConnegFilter = new UriConnegFilter(mappings, null);
        register(uriConnegFilter);

        //OUR OWN FILTERS
        final BookResponseFilter responseFilter = new BookResponseFilter();
        packages("com.Book").
                register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(responseFilter).to(BookResponseFilter.class);
                    }
                });
        packages("com.Book").property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }
}
