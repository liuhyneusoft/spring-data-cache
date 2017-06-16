package com.liuhy.springdatacache.service;

import java.util.List;

import com.liuhy.springdatacache.domain.Book;
import com.liuhy.springdatacache.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class BookServiceImpl  implements BookService{
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    //将缓存保存进andCache，并使用参数中的bid加上一个字符串(这里使用方法名称)作为缓存的key，缺省按照函数的所有参数组合作为key值
    //#bid代表参数bid，#p0 代表第一个参数
    //unless 和condition的区别是，unless是在函数调用后判断，可以对result判断。
    //cacheManager  制定使用哪个缓存管理器
    //cacheResolver缓存解析器，创建类实现org.springframework.cache.interceptor.CacheResolver接口
    @Cacheable(value="andCache",key="#bid+'findById'")
    public Book findById(Integer bid) {
        return bookRepository.findById(bid);
    }

    //在cacheManager中有currentHashMap属性，
    // 有两个key分别是gege和ancCahce，value分别都是concurrentHashmap，key是这个方法制定的。value是Cache对象
    @Cacheable(value="gege",key="#bid+'findById'")
    public Book findByIdGeGe(Integer bid) {
        return bookRepository.findById(bid);
    }

    //将缓存保存进andCache，并当参数title的长度大于4时才保存进缓存，
    @Cacheable(value="andCache",condition="#title.length() > 4")
    public Book findByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    //清除掉指定key中的缓存
    @CacheEvict(value="andCache",key="#book.id + 'findById'")
    public void modifyBook(Book book) {
        log.info("清除指定缓存"+book.getId()+"findById");
        bookRepository.save(book);
    }

    //清除掉全部缓存
    @CacheEvict(value="andCache",allEntries=true,beforeInvocation=true)
    public void ReservedBook() {
        log.info("清除全部的缓存");
    }

    @Cacheable(value="andCacheList",key="#id+'findById'")
    public List<Book> findByIdLessThan(int id){
       List<Book> listBooks = bookRepository.findByIdLessThan(3);
       return listBooks;
    }

    /**
     * 新增
     */
    public void insertBook(Book book){
        bookRepository.save(book);
    }

    @Override
    public List<Book> findBookAll() {
        return bookRepository.findBookAll();
    }

}
