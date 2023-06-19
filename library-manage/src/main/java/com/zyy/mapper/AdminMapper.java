package com.zyy.mapper;

import com.zyy.dto.LoginDTO;
import com.zyy.dto.PageDTO;
import com.zyy.dto.UpdatePassDTO;
import com.zyy.pojo.Admin;
import com.zyy.pojo.User;
import com.zyy.vo.LoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<Admin> list();

    List<Admin> pager(PageDTO pageDTO);

    void insert(Admin admin);

    Admin getById(Integer id);

    void update(Admin admin);



    Admin getLoginInfo(@Param("username") String username, @Param("password") String password);

    Admin getByUsername(String username);

    int updatePass(UpdatePassDTO updatePassDTO);
}
