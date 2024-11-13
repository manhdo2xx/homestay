package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.OccupancyStatisticsEntity;
import com.homestay.entity.view.OccupancyStatisticsView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OccupancyStatisticsDao extends BaseMapper<OccupancyStatisticsEntity> {

    List<OccupancyStatisticsView> selectListView(@Param("ew") Wrapper<OccupancyStatisticsEntity> wrapper);

    List<OccupancyStatisticsView> selectListView(Pagination page, @Param("ew") Wrapper<OccupancyStatisticsEntity> wrapper);

    OccupancyStatisticsView selectView(@Param("ew") Wrapper<OccupancyStatisticsEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<OccupancyStatisticsEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<OccupancyStatisticsEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<OccupancyStatisticsEntity> wrapper);

}
