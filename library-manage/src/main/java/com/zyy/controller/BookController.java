package com.zyy.controller;


import com.zyy.common.R;
import com.zyy.dto.BookDTO;
import com.zyy.pojo.Book;
import com.zyy.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/book")
//@CrossOrigin
@Slf4j
public class BookController {
    
    @Autowired
    private BookService bookService;





    /**
     * 查询所有
     * @return
     */
    @GetMapping("/list")
    public R list() {
        List<Book> books = bookService.list();
        return R.OK(books);
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("/add")
    public R insert(@RequestBody Book book){
        bookService.insert(book);
        return R.OK();
    }

    /**
     * 更新
     * @return
     */
    @PutMapping("/update")
    public R update(@RequestBody Book book){
        bookService.update(book);
        return R.OK();
    }


    /**
     * 删除
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        bookService.delete(id);
        return R.OK();
    }


    /**
     * 根据id查询
     * @return
     */
    @GetMapping ("/{id}")
    public R getById(@PathVariable Integer id){
        Book book = bookService.getById(id);
        return R.OK(book);
    }


    /**
     * 分页
     * @return
     */
    @GetMapping("/pager")
    public R pager(BookDTO bookDTO) {
        return R.OK(bookService.pager(bookDTO));
    }



}
