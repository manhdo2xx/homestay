package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.DiscussRoomInformationEntity;
import com.homestay.entity.view.DiscussRoomInformationView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiscussRoomInformationDao extends BaseMapper<DiscussRoomInformationEntity> {

    List<DiscussRoomInformationView> selectListView(@Param("ew") Wrapper<DiscussRoomInformationEntity> wrapper);

    List<DiscussRoomInformationView> selectListView(Pagination page, @Param("ew") Wrapper<DiscussRoomInformationEntity> wrapper);

    DiscussRoomInformationView selectView(@Param("ew") Wrapper<DiscussRoomInformationEntity> wrapper);

}
