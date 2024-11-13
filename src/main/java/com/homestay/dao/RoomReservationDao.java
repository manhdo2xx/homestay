package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.RoomReservationEntity;
import com.homestay.entity.view.RoomReservationView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoomReservationDao extends BaseMapper<RoomReservationEntity> {

    List<RoomReservationView> selectListView(@Param("ew") Wrapper<RoomReservationEntity> wrapper);

    List<RoomReservationView> selectListView(Pagination page, @Param("ew") Wrapper<RoomReservationEntity> wrapper);

    RoomReservationView selectView(@Param("ew") Wrapper<RoomReservationEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<RoomReservationEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<RoomReservationEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<RoomReservationEntity> wrapper);



}
