package com.liuhy.springdatacache.repository;

import com.liuhy.springdatacache.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b  where 1=1")
    public List<Book> findBookAll();

    @Query("select  b from Book b where b.id =?1")
    public  Book findById(Integer bid);

    @Query("select  b from Book b where b.title =?1")
    public Book findByTitle(String title);

    /**
     * //符合逻辑，不必自己写实现方法
     */
    public List<Book> findByIdLessThan(int id);

}