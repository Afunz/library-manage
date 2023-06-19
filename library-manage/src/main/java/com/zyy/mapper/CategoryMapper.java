package com.zyy.mapper;

import com.zyy.dto.PageDTO;
import com.zyy.pojo.Category;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> list();

    List<Category> pager(PageDTO pageDTO);

    void insert(Category category);

    Category getById(Integer id);

    void update(Category category);

    void delete(Integer id);
}
