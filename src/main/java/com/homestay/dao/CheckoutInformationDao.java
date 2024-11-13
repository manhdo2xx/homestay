package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.CheckoutInformationEntity;
import com.homestay.entity.view.CheckoutInformationView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckoutInformationDao extends BaseMapper<CheckoutInformationEntity> {

    List<CheckoutInformationView> selectListView(@Param("ew") Wrapper<CheckoutInformationEntity> wrapper);

    List<CheckoutInformationView> selectListView(Pagination page, @Param("ew") Wrapper<CheckoutInformationEntity> wrapper);

    CheckoutInformationView selectView(@Param("ew") Wrapper<CheckoutInformationEntity> wrapper);


}