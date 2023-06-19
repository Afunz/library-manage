package com.zyy.mapper;


import com.zyy.dto.PageDTO;
import com.zyy.pojo.ReturnBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReturnBookMapper {

    List<ReturnBook> pager(PageDTO pageDTO);

    void insert(ReturnBook returnBook);

    void delete(Integer id);
}
