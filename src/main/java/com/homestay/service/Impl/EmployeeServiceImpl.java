package com.homestay.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.homestay.dao.EmployeeDao;
import com.homestay.entity.EmployeeEntity;
import com.homestay.entity.view.EmployeeView;
import com.homestay.service.EmployeeService;
import com.homestay.utils.PageUtils;
import com.homestay.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("employeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao, EmployeeEntity> implements EmployeeService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EmployeeEntity> page = this.selectPage(
                new Query<EmployeeEntity>(params).getPage(),
                new EntityWrapper<EmployeeEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<EmployeeEntity> wrapper) {
        Page<EmployeeView> page =new Query<EmployeeView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<EmployeeView> selectListView(Wrapper<EmployeeEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public EmployeeView selectView(Wrapper<EmployeeEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
