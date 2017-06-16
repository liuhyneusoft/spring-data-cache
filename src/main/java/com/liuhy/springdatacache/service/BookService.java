package com.liuhy.springdatacache.service;


import com.liuhy.springdatacache.domain.Book;

import java.util.List;

public interface BookService {

    public Book findById(Integer bid);
    public Book findByIdGeGe(Integer bid);

    public List<Book> findBookAll();

    public void insertBook(Book book);

    public Book findByTitle(String title);

    public void modifyBook(Book book);

    public List<Book> findByIdLessThan(int id);
}