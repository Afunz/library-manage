package com.zyy.mapper;

import com.zyy.dto.PageDTO;
import com.zyy.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> list();

    List<Book> pager(PageDTO pageDTO);

    void insert(Book book);

    Book getById(Integer id);

    void update(Book book);

    void delete(Integer id);

    Book getBookId(String bookId);

    void updateAmount(String bookId);
}
