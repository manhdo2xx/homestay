package com.homestay.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.homestay.dao.CheckinInformationDao;
import com.homestay.entity.CheckinInformationEntity;
import com.homestay.entity.view.CheckinInformationView;
import com.homestay.service.CheckinInformationService;
import com.homestay.utils.PageUtils;
import com.homestay.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("checkinInformationService")
public class CheckinInformationServiceImpl extends ServiceImpl<CheckinInformationDao, CheckinInformationEntity> implements CheckinInformationService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CheckinInformationEntity> page = this.selectPage(
                new Query<CheckinInformationEntity>(params).getPage(),
                new EntityWrapper<CheckinInformationEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<CheckinInformationEntity> wrapper) {
        Page<CheckinInformationView> page =new Query<CheckinInformationView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<CheckinInformationView> selectListView(Wrapper<CheckinInformationEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public CheckinInformationView selectView(Wrapper<CheckinInformationEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<CheckinInformationEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<CheckinInformationEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<CheckinInformationEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }
    
}
