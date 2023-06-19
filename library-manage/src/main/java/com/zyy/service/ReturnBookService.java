package com.zyy.service;

import com.github.pagehelper.PageInfo;
import com.zyy.dto.PageDTO;
import com.zyy.pojo.ReturnBook;

public interface ReturnBookService {

    PageInfo<ReturnBook> pager(PageDTO pageDTO);

    void insert(ReturnBook returnBook);


    void delete(Integer id);
}
