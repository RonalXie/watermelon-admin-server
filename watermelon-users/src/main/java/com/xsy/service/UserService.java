package com.xsy.service;

import com.xsy.entity.User;

import java.util.List;

/**
 * 用户(User)表服务接口
 *
 * @author makejava
 * @since 2021-08-05 14:14:30
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询多条数据
     * @param page 查询起始页
     * @param pageSize  查询条数
     * @param name  name查询
     * @param phone  phone查询
     * @return 对象列表
     */
    List<User> queryAllByLimit(int page,int pageSize,String name,String phone);
    int queryCount(String name,String phone);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
