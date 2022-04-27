package com.example.mlearn;

import com.example.mlearn.Book;

interface IBookManager {

    List<Book> getBookList();
    void addbook(in Book book);

}