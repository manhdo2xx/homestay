package com.homestay.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.homestay.dao.RoomTypeDao;
import com.homestay.entity.RoomTypeEntity;
import com.homestay.entity.view.RoomTypeView;
import com.homestay.service.RoomTypeService;
import com.homestay.utils.PageUtils;
import com.homestay.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("roomTypeService")
public class RoomTypeServiceImpl extends ServiceImpl<RoomTypeDao, RoomTypeEntity> implements RoomTypeService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RoomTypeEntity> page = this.selectPage(
                new Query<RoomTypeEntity>(params).getPage(),
                new EntityWrapper<RoomTypeEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<RoomTypeEntity> wrapper) {
        Page<RoomTypeView> page =new Query<RoomTypeView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<RoomTypeView> selectListView(Wrapper<RoomTypeEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public RoomTypeView selectView(Wrapper<RoomTypeEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
