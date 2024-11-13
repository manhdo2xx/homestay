package com.homestay.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.homestay.dao.RoomInformationDao;
import com.homestay.entity.RoomInformationEntity;
import com.homestay.entity.view.RoomInformationView;
import com.homestay.service.RoomInformationService;
import com.homestay.utils.PageUtils;
import com.homestay.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("RoomInformationService")
public class RoomInformationServiceImpl extends ServiceImpl<RoomInformationDao, RoomInformationEntity> implements RoomInformationService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RoomInformationEntity> page = this.selectPage(
                new Query<RoomInformationEntity>(params).getPage(),
                new EntityWrapper<RoomInformationEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<RoomInformationEntity> wrapper) {
        Page<RoomInformationView> page =new Query<RoomInformationView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<RoomInformationView> selectListView(Wrapper<RoomInformationEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public RoomInformationView selectView(Wrapper<RoomInformationEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<RoomInformationEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<RoomInformationEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<RoomInformationEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }
    
}
