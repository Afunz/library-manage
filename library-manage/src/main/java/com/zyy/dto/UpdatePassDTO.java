package com.zyy.dto;

import lombok.Data;

@Data
public class UpdatePassDTO {
    private String username;
    private String password;
    private String newPass;
}
