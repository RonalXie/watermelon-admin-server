package com.xsy.controller;

import com.xsy.entity.Category;
import com.xsy.entity.CommonResult;
import com.xsy.entity.vo.BaseCategory;
import com.xsy.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 分类(Category)表控制层
 *
 * @author makejava
 * @since 2021-08-05 12:46:09
 */
@RestController
public class CategoryController {
    /**
     * 服务对象
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Category selectOne(Integer id) {
        return this.categoryService.queryById(id);
    }

    @GetMapping("/categories")
    public CommonResult<List> categories(String token){
        List<Category> categories=categoryService.queryCategories();
        return new CommonResult<List>(200,"获取列表成功",categories);
    }

    @PatchMapping("/categories/{id}")
    public CommonResult<Category> update(@PathVariable("id") Integer id,@RequestBody Category category){
        category.setId(id);
        category.setUpdatedAt(new Date());
        Category categoryUpdate = categoryService.update(category);
        return new CommonResult<Category>(200,"修改成功!",categoryUpdate);
    }
    @PostMapping("/categories")
    public CommonResult<BaseCategory> insert(@RequestBody Category category){
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());
        Category categoryInsert = categoryService.insert(category);
        BaseCategory baseCategory=new BaseCategory();
        BeanUtils.copyProperties(categoryInsert,baseCategory);
        return new CommonResult<BaseCategory>(200,"添加成功!",baseCategory);
    }

    @DeleteMapping("categories/{id}")
    public CommonResult<Void> delete(@PathVariable Integer id){
        Boolean flag=categoryService.deleteById(id);
        if (flag){
            return new CommonResult<Void>(200,"删除成功!");
        }
        return new CommonResult<Void>(500,"删除失败!");



    }

}
