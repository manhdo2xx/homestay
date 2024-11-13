package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.EmployeeEntity;
import com.homestay.entity.view.EmployeeView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao extends BaseMapper<EmployeeEntity> {

    List<EmployeeView> selectListView(@Param("ew") Wrapper<EmployeeEntity> wrapper);

    List<EmployeeView> selectListView(Pagination page, @Param("ew") Wrapper<EmployeeEntity> wrapper);

    EmployeeView selectView(@Param("ew") Wrapper<EmployeeEntity> wrapper);


}
