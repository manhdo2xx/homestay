package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.MenuEntity;
import com.homestay.entity.view.MenuView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao extends BaseMapper<MenuEntity> {

    List<MenuView> selectListView(@Param("ew") Wrapper<MenuEntity> wrapper);

    List<MenuView> selectListView(Pagination page, @Param("ew") Wrapper<MenuEntity> wrapper);

    MenuView selectView(@Param("ew") Wrapper<MenuEntity> wrapper);


}
