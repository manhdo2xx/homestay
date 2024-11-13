package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.OccupancyStatisticsEntity;
import com.homestay.entity.view.OccupancyStatisticsView;
import com.homestay.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OccupancyStatisticsService extends IService<OccupancyStatisticsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<OccupancyStatisticsView> selectListView(Wrapper<OccupancyStatisticsEntity> wrapper);

    OccupancyStatisticsView selectView(@Param("ew") Wrapper<OccupancyStatisticsEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<OccupancyStatisticsEntity> wrapper);

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<OccupancyStatisticsEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<OccupancyStatisticsEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<OccupancyStatisticsEntity> wrapper);
    
}
