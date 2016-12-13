package com.book;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Created by Lavanya Kondragunta on 10/30/2015.
 */
public class BookClient {
    private Client client;

    public BookClient() {
        client = ClientBuilder.newClient();
    }

    public Book get(String id) {
        WebTarget target = client.target("http://localhost:8080/jersey/rest/");
        return target.path("allbooks/" + id).request().get(Book.class);

    }

}
