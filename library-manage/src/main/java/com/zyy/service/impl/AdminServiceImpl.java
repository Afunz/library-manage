package com.zyy.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyy.dto.LoginDTO;
import com.zyy.dto.PageDTO;
import com.zyy.dto.UpdatePassDTO;
import com.zyy.exception.ServiceException;
import com.zyy.mapper.AdminMapper;
import com.zyy.mapper.UserMapper;
import com.zyy.pojo.Admin;
import com.zyy.pojo.User;
import com.zyy.service.AdminService;
import com.zyy.service.UserService;
import com.zyy.utils.JwtUtils;
import com.zyy.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    private static final String DEFAULT_PASSWORD = "123456";
    private static final String SALT = "zyy+!";

    @Override
    public List<Admin> list() {
        return adminMapper.list();
    }

    @Override
    public PageInfo<Admin> pager(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<Admin> admins = adminMapper.pager(pageDTO);
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        return adminPageInfo;
    }

    @Override
    public void insert(Admin admin) {
        if (StrUtil.isBlank(admin.getPassword())) {
            admin.setPassword(DEFAULT_PASSWORD);
        }
        admin.setPassword(md5Pass(admin.getPassword()));  // 设置md5加密，加盐
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            log.error("数据插入失败， username:{}", admin.getUsername(), e);
            throw new ServiceException("用户名重复");
        }



    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin admin) {
        admin.setUpdatetime(LocalDate.now());
        adminMapper.update(admin);
    }


    @Override
    public LoginVo login(LoginDTO loginDTO) {
        Admin admin = null;
        try {
            admin = adminMapper.getByUsername(loginDTO.getUsername());
        } catch (Exception e) {
            log.error("根据用户名{} 查询出错", loginDTO.getUsername());
            throw new ServiceException("用户名错误");
        }
        if (admin == null) {
            throw new ServiceException("用户名或密码错误");
        }
        // 判断密码是否合法
        String md5Pass = md5Pass(loginDTO.getPassword());
        if (!md5Pass.equals(admin.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(admin, loginVo);

        // 生成token
        String token = JwtUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());
        System.out.println(token);
        loginVo.setToken(token);
        return loginVo;

    }

    @Override
    public void updatePass(UpdatePassDTO updatePassDTO) {
        updatePassDTO.setNewPass(md5Pass(updatePassDTO.getNewPass()));
        int count = adminMapper.updatePass(updatePassDTO);
        if (count <= 0) {
            throw new ServiceException("修改密码失败");
        }
    }

    private String md5Pass(String password) {
        return SecureUtil.md5(password + SALT); // 设置md5加密，加盐
    }
}
