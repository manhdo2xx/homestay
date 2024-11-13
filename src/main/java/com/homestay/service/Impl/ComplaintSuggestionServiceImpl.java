package com.homestay.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.homestay.dao.ComplaintSuggestionDao;
import com.homestay.entity.ComplaintSuggestionEntity;
import com.homestay.entity.view.ComplaintSuggestionView;
import com.homestay.service.ComplaintSuggestionService;
import com.homestay.utils.PageUtils;
import com.homestay.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("complaintSuggestionService")
public class ComplaintSuggestionServiceImpl extends ServiceImpl<ComplaintSuggestionDao, ComplaintSuggestionEntity> implements ComplaintSuggestionService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ComplaintSuggestionEntity> page = this.selectPage(
                new Query<ComplaintSuggestionEntity>(params).getPage(),
                new EntityWrapper<ComplaintSuggestionEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ComplaintSuggestionEntity> wrapper) {
        Page<ComplaintSuggestionView> page =new Query<ComplaintSuggestionView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<ComplaintSuggestionView> selectListView(Wrapper<ComplaintSuggestionEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ComplaintSuggestionView selectView(Wrapper<ComplaintSuggestionEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
