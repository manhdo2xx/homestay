package com.homestay.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.homestay.dao.RoomReservationDao;
import com.homestay.entity.RoomReservationEntity;
import com.homestay.entity.view.RoomReservationView;
import com.homestay.service.RoomReservationService;
import com.homestay.utils.PageUtils;
import com.homestay.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("RoomReservationService")
public class RoomReservationServiceImpl extends ServiceImpl<RoomReservationDao, RoomReservationEntity> implements RoomReservationService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RoomReservationEntity> page = this.selectPage(
                new Query<RoomReservationEntity>(params).getPage(),
                new EntityWrapper<RoomReservationEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<RoomReservationEntity> wrapper) {
        Page<RoomReservationView> page = new Query<RoomReservationView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<RoomReservationView> selectListView(Wrapper<RoomReservationEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public RoomReservationView selectView(Wrapper<RoomReservationEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<RoomReservationEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<RoomReservationEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<RoomReservationEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }
}
