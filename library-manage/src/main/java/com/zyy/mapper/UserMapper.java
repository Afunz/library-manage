package com.zyy.mapper;

import com.zyy.dto.PageDTO;
import com.zyy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> list();

    List<User> pager(PageDTO pageDTO);

    void insert(User user);

    User getById(Integer id);

    void update(User user);

    void delete(Integer id);

}
