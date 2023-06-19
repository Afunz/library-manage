package com.zyy.service.impl;


import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyy.dto.PageDTO;
import com.zyy.exception.ServiceException;
import com.zyy.mapper.CategoryMapper;
import com.zyy.pojo.Category;
import com.zyy.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> list() {
        return categoryMapper.list();
    }

    @Override
    public PageInfo<Category> pager(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<Category> categories = categoryMapper.pager(pageDTO);
        PageInfo<Category> categoryPageInfo = new PageInfo<>(categories);
        return categoryPageInfo;
    }

    @Override
    public void insert(Category category) {
        categoryMapper.insert(category);

    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.getById(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdatetime(LocalDate.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
