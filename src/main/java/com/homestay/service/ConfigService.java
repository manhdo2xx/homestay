package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.ConfigEntity;
import com.homestay.utils.PageUtils;

import java.util.Map;

public interface ConfigService extends IService<ConfigEntity> {
    PageUtils queryPage(Map<String, Object> params, Wrapper<ConfigEntity> wrapper);
}
