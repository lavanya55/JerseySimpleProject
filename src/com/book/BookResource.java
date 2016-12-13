package com.book;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Collection;

/**
 * Created by Lavanya Kondragunta on 10/29/2015.
 */
@Path("/allbooks")    //http://localhost:8080/jersey/rest/allbooks
public class BookResource {
    //WITHOUT DEPENDENCY INJECTION
    private  BookDao bookDao = new BookDao();

    //DEPENDENCY INJECTION EXAMPLE WITH HK2
    @Context    //This annotation is used to inject information into a class field, bean property or method parameter.
    BookDao bookDaoWithDI;

    //GET ALL Example
    @GET  //http://localhost:8080/jersey/rest/allbooks
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Book> getBooks() {
       return bookDaoWithDI.getAllBooks();
    }

    //GET SPECIFIC EXAMPLE
    @PoweredBy("Lavanya")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{bookId}")  //http://localhost:8080/jersey/rest/allbooks/1234
    public Book getBook(@PathParam("bookId") String bookId) {
   //     return bookDao.getBook(bookId);
      return bookDaoWithDI.getBook(bookId);
    }

    //POST EXAMPLES
    @POST
    @Path("addBookForm") //http://localhost:8080/jersey/rest/allbooks/addBookForm
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Book addBook(@Valid MultivaluedMap<String, String> formParams) {
        Book book = new Book();
        book.setAuthor(formParams.getFirst("author"));
        book.setId(formParams.getFirst("id"));
        book.setTitle(formParams.getFirst("title"));
        bookDao.addNewBook(book);
        return book;
    }

    @POST
    @Path("addBookJson")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book addBookJson(@Valid Book book) {
        bookDao.addNewBook(book);
        return book;
    }


    // BEAN VALIDATION EXAMPLE USING HIBERNATE VALIDATOR

    // FILTERS








}
