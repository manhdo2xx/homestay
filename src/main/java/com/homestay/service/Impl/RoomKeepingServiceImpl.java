package com.homestay.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.homestay.dao.RoomKeepingDao;
import com.homestay.entity.RoomKeepingEntity;
import com.homestay.entity.view.RoomKeepingView;
import com.homestay.service.RoomKeepingService;
import com.homestay.utils.PageUtils;
import com.homestay.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("roomKeepingService")
public class RoomKeepingServiceImpl extends ServiceImpl<RoomKeepingDao, RoomKeepingEntity> implements RoomKeepingService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RoomKeepingEntity> page = this.selectPage(
                new Query<RoomKeepingEntity>(params).getPage(),
                new EntityWrapper<RoomKeepingEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<RoomKeepingEntity> wrapper) {
        Page<RoomKeepingView> page =new Query<RoomKeepingView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<RoomKeepingView> selectListView(Wrapper<RoomKeepingEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public RoomKeepingView selectView(Wrapper<RoomKeepingEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
