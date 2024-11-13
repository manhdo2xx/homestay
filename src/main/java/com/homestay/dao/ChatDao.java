package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.ChatEntity;
import com.homestay.entity.view.ChatView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatDao extends BaseMapper<ChatEntity> {

    List<ChatView> selectListView(@Param("ew") Wrapper<ChatEntity> wrapper);

    List<ChatView> selectListView(Pagination page, @Param("ew") Wrapper<ChatEntity> wrapper);

    ChatView selectView(@Param("ew") Wrapper<ChatEntity> wrapper);

}
