package com.homestay.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.homestay.dao.MenuDao;
import com.homestay.entity.MenuEntity;
import com.homestay.entity.view.MenuView;
import com.homestay.service.MenuService;
import com.homestay.utils.PageUtils;
import com.homestay.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MenuEntity> page = this.selectPage(
                new Query<MenuEntity>(params).getPage(),
                new EntityWrapper<MenuEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<MenuEntity> wrapper) {
        Page<MenuView> page =new Query<MenuView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<MenuView> selectListView(Wrapper<MenuEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public MenuView selectView(Wrapper<MenuEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}