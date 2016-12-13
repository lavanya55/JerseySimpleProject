package com.book;


import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

/**
 * Created by Lavanya Kondragunta on 10/30/2015.
 */
public class BookTest {
    @Test
    public void testGetBook_Dependency_Injection() {
        BookClient bookClient = new BookClient();
        Book book = bookClient.get("1234");
        Book book2 = bookClient.get("1234");
        System.out.println("book1 Date on first call = " + book.getPubDate());
        System.out.println("book1 Date on second call = " + book2.getPubDate());
        assertTrue(book.getPubDate().equals(book2.getPubDate()));
        // This explains that with Dependency injection(@Context) the object is injected at the time of constructor invocation and
        // will be available to access inside methods annotated with @POST, @GET etc
        //This test will fail if you change in BookResource.java->getBook(@PathParam("bookId") String bookId) method
        // uncomment the bookDao line and comment bookDaoWithDI lone which is using normal class field instead of injected field.
    }

    @Test
    public void testBuiltInFilter() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/jersey/rest/");
        Response book = target.path("allbooks/1234.xml").request().get();
        assertEquals(MediaType.APPLICATION_XML, book.getHeaderString("Content-Type"));
        book = target.path("allbooks/1234.json").request().get();
        assertEquals(MediaType.APPLICATION_JSON, book.getHeaderString("Content-Type"));
    }

    @Test
    public void testPoweredByResponseFilter() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/jersey/rest/");
        Response book = target.path("allbooks/1234").request().get();
        assertEquals("Lavanya", book.getHeaderString("X-Powered-By"));
        book = target.path("allbooks").request().get();
        assertNull(book.getHeaderString("X-Powered-By"));

    }
}
