package com.liuhy.springdatacache;

import com.liuhy.springdatacache.domain.Book;
import com.liuhy.springdatacache.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * EnableCaching  //扫描Cache注解，否则service的@Cache注解都无效。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching  //扫描Cache注解，否则service的@Cache注解都无效。
public class SpringDataCacheApplicationTests {
	@Autowired
	private BookService bookService;


	@Test
	public void test1() {
 		//只会打印一次sql，b4从缓存中取。
		Book b3 = bookService.findById(3);
		Book b4 = bookService.findById(3);

		System.err.println(b3);
		System.err.println(b4);
	}

	@Test
	public void test2() {
		//打印了2次sql，因为不满足缓存的conditon（title.length()>4）
		Book b1 = bookService.findByTitle("cccc");
		Book b2 = bookService.findByTitle("cccc");
		System.err.println(b1);
		System.err.println(b2);
		//只打印一次sql
		Book b3 = bookService.findByTitle("bbbbb");
		Book b4 = bookService.findByTitle("bbbbb");
		System.err.println(b3);
		System.err.println(b4);
	}

	@Test
	public void test3() {
		//打印一次sql
		List<Book> b1 = bookService.findByIdLessThan(3);
		List<Book> b2 = bookService.findByIdLessThan(3);
		b2.forEach(x->System.out.println(x));
	}


	@Test
	public void test4() {
		//缓存被清理，执行两次sql
		Book b3 = bookService.findById(3);
		b3.setTitle("b3Modify");
		bookService.modifyBook(b3);
		Book b4 = bookService.findById(3);

		System.err.println(b3);
		System.err.println(b4);
	}

}
