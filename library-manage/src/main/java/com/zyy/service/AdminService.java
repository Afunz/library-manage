package com.zyy.service;

import com.github.pagehelper.PageInfo;
import com.zyy.dto.LoginDTO;
import com.zyy.dto.PageDTO;
import com.zyy.dto.UpdatePassDTO;
import com.zyy.pojo.Admin;
import com.zyy.pojo.User;
import com.zyy.vo.LoginVo;

import java.util.List;

public interface AdminService {

    List<Admin> list();

    PageInfo<Admin> pager(PageDTO pageDTO);

    void insert(Admin admin);

    Admin getById(Integer id);

    void update(Admin admin);



    LoginVo login(LoginDTO loginDTO);

    void updatePass(UpdatePassDTO updatePassDTO);
}
