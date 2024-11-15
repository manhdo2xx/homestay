package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.RoomReservationEntity;
import com.homestay.entity.view.RoomReservationView;
import com.homestay.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoomReservationService extends IService<RoomReservationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<RoomReservationView> selectListView(Wrapper<RoomReservationEntity> wrapper);

    RoomReservationView selectView(@Param("ew") Wrapper<RoomReservationEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<RoomReservationEntity> wrapper);

    List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<RoomReservationEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<RoomReservationEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<RoomReservationEntity> wrapper);

}
