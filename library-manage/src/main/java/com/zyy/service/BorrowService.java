package com.zyy.service;

import com.github.pagehelper.PageInfo;
import com.zyy.dto.PageDTO;
import com.zyy.pojo.Borrow;
import com.zyy.pojo.ReturnBook;

import java.util.List;
import java.util.Map;


public interface BorrowService {
    List<Borrow> list();

    PageInfo<Borrow> pager(PageDTO pageDTO);

    void insert(Borrow borrow);



    Borrow getById(Integer id);

    void update(Borrow borrow);


    void delete(Integer id);


    Map<String, Object> getCountByTimeRange(String timeRange);
}
