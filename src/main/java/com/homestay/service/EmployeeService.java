package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.EmployeeEntity;
import com.homestay.entity.view.EmployeeView;
import com.homestay.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeService extends IService<EmployeeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<EmployeeView> selectListView(Wrapper<EmployeeEntity> wrapper);

    EmployeeView selectView(@Param("ew") Wrapper<EmployeeEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<EmployeeEntity> wrapper);


}
