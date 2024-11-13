package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.RoomInformationEntity;
import com.homestay.entity.view.RoomInformationView;
import com.homestay.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoomInformationService extends IService<RoomInformationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<RoomInformationView> selectListView(Wrapper<RoomInformationEntity> wrapper);

    RoomInformationView selectView(@Param("ew") Wrapper<RoomInformationEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<RoomInformationEntity> wrapper);


    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<RoomInformationEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<RoomInformationEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<RoomInformationEntity> wrapper);
    
}
