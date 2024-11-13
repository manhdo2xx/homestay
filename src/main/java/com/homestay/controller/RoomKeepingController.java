package com.homestay.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.RoomKeepingEntity;
import com.homestay.entity.view.RoomKeepingView;
import com.homestay.service.RoomKeepingService;
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
@RequestMapping("/roomkeeping")
public class RoomKeepingController {
    @Autowired
    private RoomKeepingService roomKeepingService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, RoomKeepingEntity roomKeepingEntity,
                  HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("employee")) {
            roomKeepingEntity.setEmployeeIdNumber((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<RoomKeepingEntity> ew = new EntityWrapper<RoomKeepingEntity>();

        PageUtils page = roomKeepingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, roomKeepingEntity), params), params));

        return R.ok().put("data", page);
    }

    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,RoomKeepingEntity roomKeepingEntity,
                  HttpServletRequest request){
        EntityWrapper<RoomKeepingEntity> ew = new EntityWrapper<RoomKeepingEntity>();

        PageUtils page = roomKeepingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, roomKeepingEntity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R list( RoomKeepingEntity roomKeepingEntity){
        EntityWrapper<RoomKeepingEntity> ew = new EntityWrapper<RoomKeepingEntity>();
        ew.allEq(MPUtil.allEQMapPre( roomKeepingEntity, "roomkeeping"));
        return R.ok().put("data", roomKeepingService.selectListView(ew));
    }

    @RequestMapping("/query")
    public R query(RoomKeepingEntity roomKeepingEntity){
        EntityWrapper< RoomKeepingEntity> ew = new EntityWrapper< RoomKeepingEntity>();
        ew.allEq(MPUtil.allEQMapPre( roomKeepingEntity, "roomkeeping"));
        RoomKeepingView roomKeepingView =  roomKeepingService.selectView(ew);
        return R.ok("Check if room cleaning is successful").put("data", roomKeepingView);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RoomKeepingEntity roomKeepingEntity  = roomKeepingService.selectById(id);
        roomKeepingEntity  = roomKeepingService.selectView(new EntityWrapper<RoomKeepingEntity>().eq("id", id));
        return R.ok().put("data", roomKeepingEntity);
    }
    
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        RoomKeepingEntity roomKeepingEntity = roomKeepingService.selectById(id);
        roomKeepingEntity = roomKeepingService.selectView(new EntityWrapper<RoomKeepingEntity>().eq("id", id));
        return R.ok().put("data", roomKeepingEntity);
    }
    
    @RequestMapping("/save")
    public R save(@RequestBody RoomKeepingEntity roomKeepingEntity, HttpServletRequest request){
        roomKeepingEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        roomKeepingService.insert(roomKeepingEntity);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody RoomKeepingEntity roomKeepingEntity, HttpServletRequest request){
        roomKeepingEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        roomKeepingService.insert(roomKeepingEntity);
        return R.ok();
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody RoomKeepingEntity roomKeepingEntity, HttpServletRequest request){
        roomKeepingService.updateById(roomKeepingEntity);
        return R.ok();
    }
    
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        roomKeepingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
}
