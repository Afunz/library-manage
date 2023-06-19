package com.zyy.controller;

import com.zyy.common.R;
import com.zyy.dto.BorrowDTO;
import com.zyy.pojo.Borrow;
import com.zyy.pojo.ReturnBook;
import com.zyy.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/borrow")
//@CrossOrigin
public class BorrowController {
    
    @Autowired
    private BorrowService borrowService;


    /**
     * 查询所有
     * @return
     */
    @GetMapping("/list")
    public R list() {
        List<Borrow> borrows = borrowService.list();
        return R.OK(borrows);
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("/add")
    public R insert(@RequestBody Borrow borrow){
        borrowService.insert(borrow);
        return R.OK();
    }

    /**
     * 更新
     * @return
     */
    @PutMapping("/update")
    public R update(@RequestBody Borrow borrow){
        borrowService.update(borrow);
        return R.OK();
    }


    /**
     * 删除
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        borrowService.delete(id);
        return R.OK();
    }


    /**
     * 根据id查询
     * @return
     */
    @GetMapping ("/{id}")
    public R getById(@PathVariable Integer id){
        Borrow borrow = borrowService.getById(id);
        return R.OK(borrow);
    }


    /**
     * 分页
     * @return
     */
    @GetMapping("/pager")
    public R pager(BorrowDTO borrowDTO) {
        return R.OK(borrowService.pager(borrowDTO));
    }

    /**
     * 图表统计
     * @return
     */
    @GetMapping("/lineCharts/{timeRange}")
    public R lineCharts(@PathVariable String timeRange){
        return  R.OK(borrowService.getCountByTimeRange(timeRange));
    }

}
