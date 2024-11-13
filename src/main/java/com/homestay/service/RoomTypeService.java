package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.RoomTypeEntity;
import com.homestay.entity.view.RoomTypeView;
import com.homestay.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoomTypeService extends IService<RoomTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<RoomTypeView> selectListView(Wrapper<RoomTypeEntity> wrapper);

    RoomTypeView selectView(@Param("ew") Wrapper<RoomTypeEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<RoomTypeEntity> wrapper);


}
