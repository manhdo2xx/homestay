package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.NewsEntity;
import com.homestay.entity.view.NewsView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsDao extends BaseMapper<NewsEntity> {

    List<NewsView> selectListView(@Param("ew") Wrapper<NewsEntity> wrapper);

    List<NewsView> selectListView(Pagination page, @Param("ew") Wrapper<NewsEntity> wrapper);

    NewsView selectView(@Param("ew") Wrapper<NewsEntity> wrapper);


}
