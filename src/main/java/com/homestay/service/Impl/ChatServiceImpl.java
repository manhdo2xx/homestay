package com.homestay.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.homestay.dao.ChatDao;
import com.homestay.entity.ChatEntity;
import com.homestay.entity.view.ChatView;
import com.homestay.service.ChatService;
import com.homestay.utils.PageUtils;
import com.homestay.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("chatService")
public class ChatServiceImpl extends ServiceImpl<ChatDao, ChatEntity> implements ChatService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChatEntity> page = this.selectPage(
                new Query<ChatEntity>(params).getPage(),
                new EntityWrapper<ChatEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ChatEntity> wrapper) {
        Page<ChatView> page =new Query<ChatView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<ChatView> selectListView(Wrapper<ChatEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ChatView selectView(Wrapper<ChatEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
