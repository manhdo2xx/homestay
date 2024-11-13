package com.homestay.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.homestay.dao.OccupancyStatisticsDao;
import com.homestay.entity.OccupancyStatisticsEntity;
import com.homestay.entity.view.OccupancyStatisticsView;
import com.homestay.service.OccupancyStatisticsService;
import com.homestay.utils.PageUtils;
import com.homestay.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("OccupancyStatisticsService")
public class OccupancyStatisticsServiceImpl extends ServiceImpl<OccupancyStatisticsDao, OccupancyStatisticsEntity> implements OccupancyStatisticsService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OccupancyStatisticsEntity> page = this.selectPage(
                new Query<OccupancyStatisticsEntity>(params).getPage(),
                new EntityWrapper<OccupancyStatisticsEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<OccupancyStatisticsEntity> wrapper) {
        Page<OccupancyStatisticsView> page =new Query<OccupancyStatisticsView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<OccupancyStatisticsView> selectListView(Wrapper<OccupancyStatisticsEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public OccupancyStatisticsView selectView(Wrapper<OccupancyStatisticsEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<OccupancyStatisticsEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<OccupancyStatisticsEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<OccupancyStatisticsEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
