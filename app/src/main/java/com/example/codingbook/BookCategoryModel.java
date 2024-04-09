package com.example.codingbook;

public class BookCategoryModel {
    String BookID,Book_name,Book_img;

    public BookCategoryModel() {
    }

    public BookCategoryModel(String bookID, String book_name, String book_img) {
        BookID = bookID;
        Book_name = book_name;
        Book_img = book_img;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String bookID) {
        BookID = bookID;
    }

    public String getBook_name() {
        return Book_name;
    }

    public void setBook_name(String book_name) {
        Book_name = book_name;
    }

    public String getBook_img() {
        return Book_img;
    }

    public void setBook_img(String book_img) {
        Book_img = book_img;
    }
}
