package com.homestay.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.CheckoutInformationEntity;
import com.homestay.entity.view.CheckoutInformationView;
import com.homestay.service.CheckoutInformationService;
import com.homestay.utils.MPUtil;
import com.homestay.utils.PageUtils;
import com.homestay.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/checkoutinformation")
public class CheckoutInformationController {
    @Autowired
    private CheckoutInformationService checkoutInformationService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, CheckoutInformationEntity checkoutInformationEntity,
                  HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            checkoutInformationEntity.setUserAccount((String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("employee")) {
            checkoutInformationEntity.setEmployeeIdNumber((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<CheckoutInformationEntity> ew = new EntityWrapper<CheckoutInformationEntity>();

        PageUtils page = checkoutInformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, checkoutInformationEntity), params), params));

        return R.ok().put("data", page);
    }

    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,CheckoutInformationEntity checkoutInformationEntity,
                  HttpServletRequest request){
        EntityWrapper<CheckoutInformationEntity> ew = new EntityWrapper<CheckoutInformationEntity>();

        PageUtils page = checkoutInformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, checkoutInformationEntity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R list( CheckoutInformationEntity checkoutInformationEntity){
        EntityWrapper<CheckoutInformationEntity> ew = new EntityWrapper<CheckoutInformationEntity>();
        ew.allEq(MPUtil.allEQMapPre( checkoutInformationEntity, "checkoutinformation"));
        return R.ok().put("data", checkoutInformationService.selectListView(ew));
    }

    @RequestMapping("/query")
    public R query(CheckoutInformationEntity checkoutInformationEntity){
        EntityWrapper< CheckoutInformationEntity> ew = new EntityWrapper< CheckoutInformationEntity>();
        ew.allEq(MPUtil.allEQMapPre( checkoutInformationEntity, "checkoutinformation"));
        CheckoutInformationView checkoutInformationView =  checkoutInformationService.selectView(ew);
        return R.ok("Query check-out information successfully").put("data", checkoutInformationView);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CheckoutInformationEntity checkoutInformationEntity = checkoutInformationService.selectById(id);
        checkoutInformationEntity = checkoutInformationService.selectView(new EntityWrapper<CheckoutInformationEntity>().eq("id", id));
        return R.ok().put("data", checkoutInformationEntity);
    }

    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CheckoutInformationEntity checkoutInformationEntity = checkoutInformationService.selectById(id);
        checkoutInformationEntity = checkoutInformationService.selectView(new EntityWrapper<CheckoutInformationEntity>().eq("id", id));
        return R.ok().put("data", checkoutInformationEntity);
    }

    @RequestMapping("/save")
    public R save(@RequestBody CheckoutInformationEntity checkoutInformationEntity, HttpServletRequest request){
        checkoutInformationEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        checkoutInformationService.insert(checkoutInformationEntity);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody CheckoutInformationEntity checkoutInformationEntity, HttpServletRequest request){
        checkoutInformationEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        checkoutInformationService.insert(checkoutInformationEntity);
        return R.ok();
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CheckoutInformationEntity checkoutInformationEntity, HttpServletRequest request){
        checkoutInformationService.updateById(checkoutInformationEntity);
        return R.ok();
    }

    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String isApproved, @RequestParam String reviewResponse){
        List<CheckoutInformationEntity> list = new ArrayList<CheckoutInformationEntity>();
        for(Long id : ids) {
            CheckoutInformationEntity checkoutInformationEntity = checkoutInformationService.selectById(id);
            checkoutInformationEntity.setIsApproved(isApproved);
            checkoutInformationEntity.setReviewResponse(reviewResponse);
            list.add(checkoutInformationEntity);
        }
        checkoutInformationService.updateBatchById(list);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        checkoutInformationService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
