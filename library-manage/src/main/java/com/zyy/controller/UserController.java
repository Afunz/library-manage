package com.zyy.controller;


import com.zyy.dto.UserDTO;
import com.zyy.pojo.User;
import com.zyy.service.UserService;
import com.zyy.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
//@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 查询所有
     * @return
     */
    @GetMapping("/list")
    public R list() {
        List<User> users = userService.list();
        return R.OK(users);
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("/add")
    public R insert(@RequestBody User user){
        userService.insert(user);
        return R.OK();
    }

    /**
     * 更新
     * @return
     */
    @PutMapping("/update")
    public R update(@RequestBody User user){
        userService.update(user);
        return R.OK();
    }


    /**
     * 删除
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        userService.delete(id);
        return R.OK();
    }


    /**
     * 根据id查询
     * @return
     */
    @GetMapping ("/{id}")
    public R getById(@PathVariable Integer id){
        User user = userService.getById(id);
        return R.OK(user);
    }


    /**
     * 分页
     * @return
     */
    @GetMapping("/pager")
    public R pager(UserDTO userDTO) {
        return R.OK(userService.pager(userDTO));
    }
}
