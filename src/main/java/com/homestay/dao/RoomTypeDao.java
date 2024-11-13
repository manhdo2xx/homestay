package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.RoomTypeEntity;
import com.homestay.entity.view.RoomTypeView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomTypeDao extends BaseMapper<RoomTypeEntity> {

    List<RoomTypeView> selectListView(@Param("ew") Wrapper<RoomTypeEntity> wrapper);

    List<RoomTypeView> selectListView(Pagination page, @Param("ew") Wrapper<RoomTypeEntity> wrapper);

    RoomTypeView selectView(@Param("ew") Wrapper<RoomTypeEntity> wrapper);

}
