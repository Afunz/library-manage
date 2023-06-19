package com.zyy.controller;


import com.zyy.common.R;
import com.zyy.dto.ReturnBookDTO;
import com.zyy.pojo.ReturnBook;
import com.zyy.service.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/returnBook")
//@CrossOrigin
public class ReturnBookController {

    @Autowired
    private ReturnBookService returnBookService;

    @GetMapping("/pager")
    public R pager(ReturnBookDTO returnBookDTO) {
        return R.OK(returnBookService.pager(returnBookDTO));
    }

    @PostMapping("/insert")
    public R insert(@RequestBody ReturnBook returnBook) {
        returnBookService.insert(returnBook);
        return R.OK();
    }

    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id) {
        returnBookService.delete(id);
        return R.OK();
    }
}
