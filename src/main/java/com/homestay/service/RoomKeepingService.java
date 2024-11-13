package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.RoomKeepingEntity;
import com.homestay.entity.view.RoomKeepingView;
import com.homestay.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoomKeepingService extends IService<RoomKeepingEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<RoomKeepingView> selectListView(Wrapper<RoomKeepingEntity> wrapper);

    RoomKeepingView selectView(@Param("ew") Wrapper<RoomKeepingEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<RoomKeepingEntity> wrapper);


}
