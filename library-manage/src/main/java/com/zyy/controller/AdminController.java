package com.zyy.controller;


import com.zyy.common.R;
import com.zyy.dto.AdminDTO;
import com.zyy.dto.LoginDTO;
import com.zyy.dto.UpdatePassDTO;
import com.zyy.dto.UserDTO;
import com.zyy.pojo.Admin;
import com.zyy.pojo.User;
import com.zyy.service.AdminService;
import com.zyy.service.UserService;
import com.zyy.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
//@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;


    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody LoginDTO loginDTO) {
        LoginVo loginVo = adminService.login(loginDTO);
        return R.OK(loginVo);
    }



    /**
     * 查询所有
     * @return
     */
    @GetMapping("/list")
    public R list() {
        List<Admin> admins = adminService.list();
        return R.OK(admins);
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("/add")
    public R insert(@RequestBody Admin admin){
        adminService.insert(admin);
        return R.OK();
    }

    /**
     * 更新
     * @return
     */
    @PutMapping("/update")
    public R update(@RequestBody Admin admin){
        adminService.update(admin);
        return R.OK();
    }




    /**
     * 根据id查询
     * @return
     */
    @GetMapping ("/{id}")
    public R getById(@PathVariable Integer id){
        Admin admin = adminService.getById(id);
        return R.OK(admin);
    }


    /**
     * 分页
     * @return
     */
    @GetMapping("/pager")
    public R pager(AdminDTO adminDTO) {
        return R.OK(adminService.pager(adminDTO));
    }


    /**
     * 修改密码
     * @return
     */
    @PutMapping("/password")
    public R updatePass(@RequestBody UpdatePassDTO updatePassDTO) {
        adminService.updatePass(updatePassDTO);
        return R.OK();
    }
}
