package com.homestay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.homestay.entity.ComplaintSuggestionEntity;
import com.homestay.entity.view.ComplaintSuggestionView;
import com.homestay.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ComplaintSuggestionService extends IService<ComplaintSuggestionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ComplaintSuggestionView> selectListView(Wrapper<ComplaintSuggestionEntity> wrapper);

    ComplaintSuggestionView selectView(@Param("ew") Wrapper<ComplaintSuggestionEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<ComplaintSuggestionEntity> wrapper);


}