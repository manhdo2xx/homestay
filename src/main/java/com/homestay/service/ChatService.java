package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.ChatEntity;
import com.homestay.entity.view.ChatView;
import com.homestay.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChatService extends IService<ChatEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ChatView> selectListView(Wrapper<ChatEntity> wrapper);

    ChatView selectView(@Param("ew") Wrapper<ChatEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<ChatEntity> wrapper);

}
