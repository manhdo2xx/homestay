package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.UserEntity;
import com.homestay.entity.view.UserView;
import com.homestay.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<UserView> selectListView(Wrapper<UserEntity> wrapper);

    UserView selectView(@Param("ew") Wrapper<UserEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<UserEntity> wrapper);

}
