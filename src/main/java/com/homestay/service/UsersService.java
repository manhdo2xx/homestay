package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.UsersEntity;
import com.homestay.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface UsersService extends IService<UsersEntity> {
    PageUtils queryPage(Map<String, Object> params);

    List<UsersEntity> selectListView(Wrapper<UsersEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<UsersEntity> wrapper);

}
