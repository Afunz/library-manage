package com.zyy.controller;

import com.zyy.common.R;
import com.zyy.dto.CategoryDTO;
import com.zyy.pojo.Category;
import com.zyy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
//@CrossOrigin
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;


    /**
     * 查询所有
     * @return
     */
    @GetMapping("/list")
    public R list() {
        List<Category> categorys = categoryService.list();
        return R.OK(categorys);
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("/add")
    public R insert(@RequestBody Category category){
        categoryService.insert(category);
        return R.OK();
    }

    /**
     * 更新
     * @return
     */
    @PutMapping("/update")
    public R update(@RequestBody Category category){
        categoryService.update(category);
        return R.OK();
    }


    /**
     * 删除
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        categoryService.delete(id);
        return R.OK();
    }


    /**
     * 根据id查询
     * @return
     */
    @GetMapping ("/{id}")
    public R getById(@PathVariable Integer id){
        Category category = categoryService.getById(id);
        return R.OK(category);
    }


    /**
     * 分页
     * @return
     */
    @GetMapping("/pager")
    public R pager(CategoryDTO categoryDTO) {
        return R.OK(categoryService.pager(categoryDTO));
    }
}
