package com.zyy.service;

import com.github.pagehelper.PageInfo;
import com.zyy.dto.PageDTO;
import com.zyy.dto.UserDTO;
import com.zyy.pojo.User;

import java.util.List;

public interface UserService {

    List<User> list();

    PageInfo<User> pager(PageDTO pageDTO);

    void insert(User user);

    User getById(Integer id);

    void update(User user);


    void delete(Integer id);

}
