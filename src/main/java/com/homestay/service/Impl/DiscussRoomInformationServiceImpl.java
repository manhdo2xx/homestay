package com.homestay.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.homestay.dao.DiscussRoomInformationDao;
import com.homestay.entity.DiscussRoomInformationEntity;
import com.homestay.entity.view.DiscussRoomInformationView;
import com.homestay.service.DiscussRoomInformationService;
import com.homestay.utils.PageUtils;
import com.homestay.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("DiscussRoomInformationService")
public class DiscussRoomInformationServiceImpl extends ServiceImpl<DiscussRoomInformationDao, DiscussRoomInformationEntity> implements DiscussRoomInformationService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussRoomInformationEntity> page = this.selectPage(
                new Query<DiscussRoomInformationEntity>(params).getPage(),
                new EntityWrapper<DiscussRoomInformationEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussRoomInformationEntity> wrapper) {
        Page<DiscussRoomInformationView> page =new Query<DiscussRoomInformationView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<DiscussRoomInformationView> selectListView(Wrapper<DiscussRoomInformationEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public DiscussRoomInformationView selectView(Wrapper<DiscussRoomInformationEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
