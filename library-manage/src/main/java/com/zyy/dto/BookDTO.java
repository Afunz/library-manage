package com.zyy.dto;

import lombok.Data;

@Data
public class BookDTO extends PageDTO{
    private String name;
    private String bookId;
}
