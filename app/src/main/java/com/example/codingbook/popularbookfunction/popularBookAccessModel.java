package com.example.codingbook.popularbookfunction;

public class popularBookAccessModel {
    private String book_name,book_image,book_author,book_publisher,book_url,book_id;


    public popularBookAccessModel() {
    }

    public popularBookAccessModel(String book_name, String book_image, String book_author, String book_publisher, String book_url, String book_id) {
        this.book_name = book_name;
        this.book_image = book_image;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.book_url = book_url;
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_image() {
        return book_image;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }

    public String getBook_url() {
        return book_url;
    }

    public void setBook_url(String book_url) {
        this.book_url = book_url;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }
}
