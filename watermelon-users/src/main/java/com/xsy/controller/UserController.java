package com.xsy.controller;

import com.xsy.entity.CommonResult;
import com.xsy.entity.User;
import com.xsy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户(User)表控制层
 *
 * @author makejava
 * @since 2021-08-05 14:14:30
 */
@RestController
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }

    @PostMapping("/users")
    public CommonResult<Map> users(Integer page,Integer pageSize,String name,String phone){
        Map<String,Object> result=new HashMap<>();

        //按条件分页查询数据
        List<User> users = userService.queryAllByLimit(page, pageSize, name, phone);
        //按条件查询总条数
        int count=userService.queryCount(name,phone);
        result.put("total",count);
        result.put("user",users);
        return new CommonResult<Map>(200,"查询成功!",result);
    }

}
