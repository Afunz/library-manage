package com.zyy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyy.dto.PageDTO;
import com.zyy.exception.ServiceException;
import com.zyy.mapper.BookMapper;
import com.zyy.pojo.Book;
import com.zyy.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> list() {
        return bookMapper.list();
    }

    @Override
    public PageInfo<Book> pager(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<Book> categories = bookMapper.pager(pageDTO);
        PageInfo<Book> bookPageInfo = new PageInfo<>(categories);
        return bookPageInfo;
    }

    @Override
    public void insert(Book book) {
        try {
            bookMapper.insert(book);
        } catch (Exception e) {
            throw new ServiceException("数据插入错误", e);
        }

    }

    @Override
    public Book getById(Integer id) {
        return bookMapper.getById(id);
    }

    @Override
    public void update(Book book) {
        try {
            book.setUpdatetime(LocalDate.now());
            bookMapper.update(book);
        } catch (Exception e) {
            throw new ServiceException("数据更新错误", e);
        }
    }

    @Override
    public void delete(Integer id) {
        bookMapper.delete(id);
    }
}
