package com.zyy.service;

import com.github.pagehelper.PageInfo;
import com.zyy.dto.PageDTO;
import com.zyy.pojo.Book;

import java.util.List;

public interface BookService {

    List<Book> list();

    PageInfo<Book> pager(PageDTO pageDTO);

    void insert(Book book);

    Book getById(Integer id);

    void update(Book book);


    void delete(Integer id);
}
