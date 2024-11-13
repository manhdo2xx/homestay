package com.homestay.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.homestay.dao.CheckoutInformationDao;
import com.homestay.entity.CheckoutInformationEntity;
import com.homestay.entity.view.CheckoutInformationView;
import com.homestay.service.CheckoutInformationService;
import com.homestay.utils.PageUtils;
import com.homestay.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("checkoutInformationService")
public class CheckoutInformationServiceImpl extends ServiceImpl<CheckoutInformationDao, CheckoutInformationEntity> implements CheckoutInformationService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CheckoutInformationEntity> page = this.selectPage(
                new Query<CheckoutInformationEntity>(params).getPage(),
                new EntityWrapper<CheckoutInformationEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<CheckoutInformationEntity> wrapper) {
        Page<CheckoutInformationView> page =new Query<CheckoutInformationView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<CheckoutInformationView> selectListView(Wrapper<CheckoutInformationEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public CheckoutInformationView selectView(Wrapper<CheckoutInformationEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
