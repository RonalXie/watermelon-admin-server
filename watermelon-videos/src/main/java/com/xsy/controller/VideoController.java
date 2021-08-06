package com.xsy.controller;

import com.xsy.entity.CommonResult;
import com.xsy.entity.Video;
import com.xsy.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频(Video)表控制层
 *
 * @author makejava
 * @since 2021-08-05 15:36:10
 */
@RestController
public class VideoController {
    /**
     * 服务对象
     */
    @Autowired
    private VideoService videoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Video selectOne(Integer id) {
        return this.videoService.queryById(id);
    }

    @PostMapping("/videos")
    public CommonResult<Map> videos(int page,int pageSize,String title,String categoryId,String uploaderName){
        Map<String,Object> result=new HashMap<>();

        //按条件分页查询数据
        List<Video> videos = videoService.queryAllByLimit(page, pageSize, title, categoryId,uploaderName);
        //按条件查询总条数
        int count=videoService.queryCount(title, categoryId,uploaderName);
        result.put("total",count);
        result.put("video",videos);
        return new CommonResult<Map>(200,"查询成功!",result);

    }

}
