package com.xsy.service;

import com.xsy.entity.Video;

import java.util.List;

/**
 * 视频(Video)表服务接口
 *
 * @author makejava
 * @since 2021-08-05 15:36:09
 */
public interface VideoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Video queryById(Integer id);

    /**
     * 查询多条数据
     *

     * @return 对象列表
     */
    List<Video> queryAllByLimit(int page, int pageSize, String title, String categoryId,String uploaderName);
    int queryCount(String title, String categoryId,String uploaderName);

    /**
     * 新增数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    Video insert(Video video);

    /**
     * 修改数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    Video update(Video video);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    int queryCount(int page, int pageSize, String name, int categoryId, String uploaderName);
}
