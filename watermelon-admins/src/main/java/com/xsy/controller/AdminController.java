package com.xsy.controller;

import com.xsy.entity.Admin;
import com.xsy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2021-08-04 19:58:21
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    /**
     * 服务对象
     */
    @Autowired
    private AdminService adminService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Admin selectOne(Integer id) {
        return this.adminService.queryById(id);
    }

}
