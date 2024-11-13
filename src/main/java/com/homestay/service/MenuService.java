package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.MenuEntity;
import com.homestay.entity.view.MenuView;
import com.homestay.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MenuService extends IService<MenuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MenuView> selectListView(Wrapper<MenuEntity> wrapper);

    MenuView selectView(@Param("ew") Wrapper<MenuEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<MenuEntity> wrapper);

}
