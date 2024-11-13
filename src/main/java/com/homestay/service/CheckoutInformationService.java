package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.CheckoutInformationEntity;
import com.homestay.entity.view.CheckoutInformationView;
import com.homestay.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CheckoutInformationService extends IService<CheckoutInformationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CheckoutInformationView> selectListView(Wrapper<CheckoutInformationEntity> wrapper);

    CheckoutInformationView selectView(@Param("ew") Wrapper<CheckoutInformationEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<CheckoutInformationEntity> wrapper);


}
