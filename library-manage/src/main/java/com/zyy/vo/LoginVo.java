package com.zyy.vo;

import lombok.Data;

@Data
public class LoginVo {
    private Integer id;
    private String username;
    private String phone;
    private String email;
    private String token;
}
