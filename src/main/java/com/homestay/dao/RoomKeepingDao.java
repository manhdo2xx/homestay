package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.RoomKeepingEntity;
import com.homestay.entity.view.RoomKeepingView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomKeepingDao extends BaseMapper<RoomKeepingEntity> {

    List<RoomKeepingView> selectListView(@Param("ew") Wrapper<RoomKeepingEntity> wrapper);

    List<RoomKeepingView> selectListView(Pagination page, @Param("ew") Wrapper<RoomKeepingEntity> wrapper);

    RoomKeepingView selectView(@Param("ew") Wrapper<RoomKeepingEntity> wrapper);


}