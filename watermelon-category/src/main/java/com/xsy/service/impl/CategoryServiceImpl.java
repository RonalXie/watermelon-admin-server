package com.xsy.service.impl;

import com.xsy.entity.Category;
import com.xsy.dao.CategoryDao;
import com.xsy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类(Category)表服务实现类
 *
 * @author makejava
 * @since 2021-08-05 12:46:09
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Category queryById(Integer id) {
        return this.categoryDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Category> queryAllByLimit(int offset, int limit) {
        return this.categoryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category insert(Category category) {
        this.categoryDao.insert(category);
        return category;
    }

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category update(Category category) {
        this.categoryDao.update(category);
        return this.queryById(category.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        Category category=categoryDao.queryById(id);
        if (category.getParentId()!=null){
            return this.categoryDao.deleteById(id) > 0;
        }
        this.categoryDao.deleteByParentId(id);
        this.categoryDao.deleteById(id);

        return true;
    }

    @Override
    public boolean deleteByParentId(Integer id) {
        return this.categoryDao.deleteByParentId(id) > 0;
    }

    @Override
    public List<Category> queryCategories() {
        return categoryDao.queryCategories();
    }
}
