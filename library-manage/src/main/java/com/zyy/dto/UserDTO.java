package com.zyy.dto;

import lombok.Data;

@Data
public class UserDTO extends PageDTO{
    private String name;
    private String phone;
}
