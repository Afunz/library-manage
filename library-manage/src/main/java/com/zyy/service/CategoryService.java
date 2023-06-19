package com.zyy.service;

import com.github.pagehelper.PageInfo;
import com.zyy.dto.PageDTO;
import com.zyy.pojo.Admin;
import com.zyy.pojo.Category;

import java.util.List;


public interface CategoryService {
    List<Category> list();

    PageInfo<Category> pager(PageDTO pageDTO);

    void insert(Category category);

    Category getById(Integer id);

    void update(Category category);


    void delete(Integer id);
}
