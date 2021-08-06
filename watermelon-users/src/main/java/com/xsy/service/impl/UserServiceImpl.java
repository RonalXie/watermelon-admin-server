package com.xsy.service.impl;

import com.xsy.dao.UserDao;
import com.xsy.entity.User;
import com.xsy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户(User)表服务实现类
 *
 * @author makejava
 * @since 2021-08-05 14:14:30
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param page 查询起始页
     * @param pageSize  查询条数
     * @param name  name查询
     * @param phone  phone查询
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int page, int pageSize, String name, String phone){
        System.out.println(page+","+pageSize);
        int start=(page-1)*pageSize;
        System.out.println(start);
        return this.userDao.queryAllByLimit(start,pageSize,name,phone);
    }

    @Override
    public int queryCount(String name,String phone) {
        return userDao.queryCount(name,phone);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }
}
