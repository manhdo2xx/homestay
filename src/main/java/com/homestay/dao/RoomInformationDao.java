package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.RoomInformationEntity;
import com.homestay.entity.view.RoomInformationView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoomInformationDao extends BaseMapper<RoomInformationEntity> {

    List<RoomInformationView> selectListView(@Param("ew") Wrapper<RoomInformationEntity> wrapper);

    List<RoomInformationView> selectListView(Pagination page, @Param("ew") Wrapper<RoomInformationEntity> wrapper);

    RoomInformationView selectView(@Param("ew") Wrapper<RoomInformationEntity> wrapper);

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<RoomInformationEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<RoomInformationEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<RoomInformationEntity> wrapper);



}
