package com.homestay.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.RoomTypeEntity;
import com.homestay.entity.view.RoomTypeView;
import com.homestay.service.RoomTypeService;
import com.homestay.utils.MPUtil;
import com.homestay.utils.PageUtils;
import com.homestay.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/roomtype")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;


    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, RoomTypeEntity roomTypeEntity,
                  HttpServletRequest request){
        EntityWrapper<RoomTypeEntity> ew = new EntityWrapper<RoomTypeEntity>();

        PageUtils page = roomTypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, roomTypeEntity), params), params));

        return R.ok().put("data", page);
    }

    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,RoomTypeEntity roomTypeEntity,
                  HttpServletRequest request){
        EntityWrapper<RoomTypeEntity> ew = new EntityWrapper<RoomTypeEntity>();

        PageUtils page = roomTypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, roomTypeEntity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R list( RoomTypeEntity roomTypeEntity){
        EntityWrapper<RoomTypeEntity> ew = new EntityWrapper<RoomTypeEntity>();
        ew.allEq(MPUtil.allEQMapPre( roomTypeEntity, "roomType"));
        return R.ok().put("data", roomTypeService.selectListView(ew));
    }

    @RequestMapping("/query")
    public R query(RoomTypeEntity roomTypeEntity){
        EntityWrapper<RoomTypeEntity> ew = new EntityWrapper< RoomTypeEntity>();
        ew.allEq(MPUtil.allEQMapPre( roomTypeEntity, "kefangleixing"));
        RoomTypeView kefangleixingView =  roomTypeService.selectView(ew);
        return R.ok("Query room type successfully").put("data", kefangleixingView);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RoomTypeEntity roomTypeEntity = roomTypeService.selectById(id);
        roomTypeEntity = roomTypeService.selectView(new EntityWrapper<RoomTypeEntity>().eq("id", id));
        return R.ok().put("data", roomTypeEntity);
    }

    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        RoomTypeEntity roomTypeEntity = roomTypeService.selectById(id);
        roomTypeEntity = roomTypeService.selectView(new EntityWrapper<RoomTypeEntity>().eq("id", id));
        return R.ok().put("data", roomTypeEntity);
    }

    @RequestMapping("/save")
    public R save(@RequestBody RoomTypeEntity roomTypeEntity, HttpServletRequest request){
        roomTypeEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        roomTypeService.insert(roomTypeEntity);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody RoomTypeEntity roomTypeEntity, HttpServletRequest request){
        roomTypeEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        roomTypeService.insert(roomTypeEntity);
        return R.ok();
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody RoomTypeEntity roomTypeEntity, HttpServletRequest request){
        roomTypeService.updateById(roomTypeEntity);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        roomTypeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
