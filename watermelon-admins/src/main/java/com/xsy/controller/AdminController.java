package com.xsy.controller;

import com.xsy.constants.RedisPrefix;
import com.xsy.entity.Admin;
import com.xsy.entity.CommonResult;
import com.xsy.entity.vo.BaseAdmin;
import com.xsy.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2021-08-04 19:58:21
 */
@RestController
public class AdminController {
    /**
     * 服务对象
     */
    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisTemplate redisTemplate;

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

    @GetMapping("/tokens")
    public CommonResult<Map> tokens(@RequestBody Admin admin, HttpSession httpSession){

        Admin adminDB=adminService.login(admin);
        //判断是否为空
        if (ObjectUtils.isEmpty(adminDB)) return new CommonResult(500,"错误的用户名或密码");
        //判断密码
        if (!admin.getPassword().equals(adminDB.getPassword())) return new CommonResult(500,"错误的用户名或密码");
        String tokens=httpSession.getId();
        redisTemplate.opsForValue().set(RedisPrefix.TOKEN_KEY+tokens,adminDB,30, TimeUnit.MINUTES);
        Map<String,String> token=new HashMap<>();
        token.put("token",tokens);
        return new CommonResult<Map>(200,"用户名密码正确",token);
    }

    @DeleteMapping("/logout/{token}")
    public void logout(@PathVariable("token") String token){
        redisTemplate.delete(RedisPrefix.TOKEN_KEY+token);

    }

    @GetMapping("/admin-info")
    public CommonResult admin(String token){
        System.out.println(token);
        Admin admin= (Admin) redisTemplate.opsForValue().get(RedisPrefix.TOKEN_KEY+token);
        BaseAdmin baseAdmin=new BaseAdmin();
        BeanUtils.copyProperties(admin,baseAdmin);
        return new CommonResult(200,"查询成功",baseAdmin);

    }

}
