package com.zyy.dto;

import lombok.Data;

@Data
public class AdminDTO extends PageDTO{
    private String username;
    private String email;
    private String phone;
}
