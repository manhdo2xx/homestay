package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.CheckinInformationEntity;
import com.homestay.entity.view.CheckinInformationView;
import com.homestay.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CheckinInformationService extends IService<CheckinInformationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CheckinInformationView> selectListView(Wrapper<CheckinInformationEntity> wrapper);

    CheckinInformationView selectView(@Param("ew") Wrapper<CheckinInformationEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<CheckinInformationEntity> wrapper);


    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<CheckinInformationEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<CheckinInformationEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<CheckinInformationEntity> wrapper);

}
