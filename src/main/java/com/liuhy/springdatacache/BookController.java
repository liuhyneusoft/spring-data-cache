package com.liuhy.springdatacache;

import com.liuhy.springdatacache.domain.Book;
import com.liuhy.springdatacache.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class BookController {
    //private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;


    @RequestMapping("/{id}")
    public @ResponseBody
    Book index(@PathVariable("id") Integer id){
        Book b = bookService.findById(id);

        return b;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public  @ResponseBody
    List<Book> list(){
        List<Book> b = bookService.findBookAll();
        return  b;

    }
    @RequestMapping(value = "/add")
    public String  insertBook(){
        Book b = new Book();
        b.setId(4);
        b.setIsbn("1111");
        b.setTitle("相信自己");
        bookService.insertBook(b);
        return "success";

    }
    /**
     * 更新
     * @return
     */
    @RequestMapping(value = "/update")
    public String update(){
        Book b = new Book();
        b.setId(1);
        b.setIsbn("1");
        b.setTitle("爱的力量");
        bookService.modifyBook(b);
        return "success";
    }
}