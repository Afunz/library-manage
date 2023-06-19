package com.zyy.dto;

import lombok.Data;

@Data
public class BorrowDTO extends PageDTO{
    private String name;
    private String bookId;
    private String username;
}
