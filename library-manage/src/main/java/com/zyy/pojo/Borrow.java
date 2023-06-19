package com.zyy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {

    /**
     * id
     */
    private Integer id;

    /**
     * 图书名称
     */
    private String name;

    /**
     * 图书标准码
     */
    private String bookId;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户联系方式
     */
    private String phone;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate createtime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate updatetime;


    private String status;
    private Integer days;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate returnTime;
    // 提醒状态 即将到期（-1）  已到期（当天）  已过期（超过归还日期之后）
    private String warn;
}
