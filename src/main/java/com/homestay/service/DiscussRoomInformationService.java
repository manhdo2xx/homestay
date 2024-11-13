package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.DiscussRoomInformationEntity;
import com.homestay.entity.view.DiscussRoomInformationView;
import com.homestay.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DiscussRoomInformationService extends IService<DiscussRoomInformationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DiscussRoomInformationView> selectListView(Wrapper<DiscussRoomInformationEntity> wrapper);

    DiscussRoomInformationView selectView(@Param("ew") Wrapper<DiscussRoomInformationEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussRoomInformationEntity> wrapper);


}