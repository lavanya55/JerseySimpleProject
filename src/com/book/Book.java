package com.book;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Lavanya Kondragunta on 10/29/2015.
 */
@XmlRootElement    //Maps a class or an enum type to an XML element. This is required if we want this object to get converted to json for response
public class Book {

    private String id;
    @NotNull(message="title is a required feild")
    private String title;
    @NotNull(message="author is a required feild")
    private String author;
    private Date pubDate;

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
