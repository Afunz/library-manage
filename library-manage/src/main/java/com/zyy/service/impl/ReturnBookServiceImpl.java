package com.zyy.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyy.dto.PageDTO;
import com.zyy.mapper.BookMapper;
import com.zyy.mapper.BorrowMapper;
import com.zyy.mapper.ReturnBookMapper;
import com.zyy.pojo.ReturnBook;
import com.zyy.service.ReturnBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Slf4j
public class ReturnBookServiceImpl implements ReturnBookService {

    @Autowired
    private ReturnBookMapper returnBookMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    @Override
    public PageInfo<ReturnBook> pager(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        return new PageInfo<>(returnBookMapper.pager(pageDTO));
    }

    @Override
    @Transactional
    public void insert(ReturnBook returnBook) {
        // 修改状态
        returnBook.setStatus("已归还");
        borrowMapper.updateStatus("已归还", returnBook.getId());
        returnBook.setRealTime(LocalDate.now());
        returnBookMapper.insert(returnBook);
        // 图书数量增加
        bookMapper.updateAmount(returnBook.getBookId());

    }

    @Override
    public void delete(Integer id) {
        returnBookMapper.delete(id);
    }
}
