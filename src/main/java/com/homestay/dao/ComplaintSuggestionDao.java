package com.homestay.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.homestay.entity.ComplaintSuggestionEntity;
import com.homestay.entity.view.ComplaintSuggestionView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComplaintSuggestionDao extends BaseMapper<ComplaintSuggestionEntity> {

    List<ComplaintSuggestionView> selectListView(@Param("ew") Wrapper<ComplaintSuggestionEntity> wrapper);

    List<ComplaintSuggestionView> selectListView(Pagination page, @Param("ew") Wrapper<ComplaintSuggestionEntity> wrapper);

    ComplaintSuggestionView selectView(@Param("ew") Wrapper<ComplaintSuggestionEntity> wrapper);


}
