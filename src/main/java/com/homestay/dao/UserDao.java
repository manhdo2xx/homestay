package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.UserEntity;
import com.homestay.entity.view.UserView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao extends BaseMapper<UserEntity> {

    List<UserView> selectListView(@Param("ew") Wrapper<UserEntity> wrapper);

    List<UserView> selectListView(Pagination page, @Param("ew") Wrapper<UserEntity> wrapper);

    UserView selectView(@Param("ew") Wrapper<UserEntity> wrapper);

}