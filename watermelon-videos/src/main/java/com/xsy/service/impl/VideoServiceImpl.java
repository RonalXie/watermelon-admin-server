package com.xsy.service.impl;

import com.xsy.entity.Video;
import com.xsy.dao.VideoDao;
import com.xsy.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 视频(Video)表服务实现类
 *
 * @author makejava
 * @since 2021-08-05 15:36:10
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao videoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Video queryById(Integer id) {
        return this.videoDao.queryById(id);
    }

    /**
     * 查询多条数据

     * @return 对象列表
     */
    @Override
    public List<Video> queryAllByLimit(int page, int pageSize, String title, String categoryId,String uploaderName) {
        int start=(page-1)*pageSize;
        return this.videoDao.queryAllByLimit(start, pageSize, title, categoryId,uploaderName);
    }

    @Override
    public int queryCount(String title, String categoryId, String uploaderName) {
        return videoDao.queryCount(title,categoryId,uploaderName);
    }

    /**
     * 新增数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    @Override
    public Video insert(Video video) {
        this.videoDao.insert(video);
        return video;
    }

    /**
     * 修改数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    @Override
    public Video update(Video video) {
        this.videoDao.update(video);
        return this.queryById(video.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.videoDao.deleteById(id) > 0;
    }

    @Override
    public int queryCount(int page, int pageSize, String name, int categoryId, String uploaderName) {
        return 0;
    }
}
