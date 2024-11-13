package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.CheckinInformationEntity;
import com.homestay.entity.view.CheckinInformationView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CheckinInformationDao extends BaseMapper<CheckinInformationEntity> {

    List<CheckinInformationView> selectListView(@Param("ew") Wrapper<CheckinInformationEntity> wrapper);

    List<CheckinInformationView> selectListView(Pagination page, @Param("ew") Wrapper<CheckinInformationEntity> wrapper);

    CheckinInformationView selectView(@Param("ew") Wrapper<CheckinInformationEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<CheckinInformationEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<CheckinInformationEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<CheckinInformationEntity> wrapper);



}
