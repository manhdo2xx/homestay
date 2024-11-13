package com.homestay.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.RoomReservationEntity;
import com.homestay.entity.view.RoomReservationView;
import com.homestay.service.RoomReservationService;
import com.homestay.utils.MPUtil;
import com.homestay.utils.PageUtils;
import com.homestay.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/roomreservation")
public class RoomReservationController {
    @Autowired
    private RoomReservationService roomReservationService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, RoomReservationEntity roomReservationEntity,
                  HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            roomReservationEntity.setUserAccount((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<RoomReservationEntity> ew = new EntityWrapper<RoomReservationEntity>();

        PageUtils page = roomReservationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, roomReservationEntity), params), params));

        return R.ok().put("data", page);
    }

    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,RoomReservationEntity roomReservationEntity,
                  HttpServletRequest request){
        EntityWrapper<RoomReservationEntity> ew = new EntityWrapper<RoomReservationEntity>();

        PageUtils page = roomReservationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, roomReservationEntity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R list( RoomReservationEntity roomReservationEntity){
        EntityWrapper<RoomReservationEntity> ew = new EntityWrapper<RoomReservationEntity>();
        ew.allEq(MPUtil.allEQMapPre( roomReservationEntity, "roomreservation"));
        return R.ok().put("data", roomReservationService.selectListView(ew));
    }

    @RequestMapping("/query")
    public R query(RoomReservationEntity roomReservationEntity){
        EntityWrapper< RoomReservationEntity> ew = new EntityWrapper< RoomReservationEntity>();
        ew.allEq(MPUtil.allEQMapPre( roomReservationEntity, "roomreservation"));
        RoomReservationView roomReservationEntityView =  roomReservationService.selectView(ew);
        return R.ok("Inquiry room reservation successful").put("data", roomReservationEntityView);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RoomReservationEntity roomReservationEntity = roomReservationService.selectById(id);
        roomReservationEntity = roomReservationService.selectView(new EntityWrapper<RoomReservationEntity>().eq("id", id));
        return R.ok().put("data", roomReservationEntity);
    }

    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        RoomReservationEntity roomReservationEntity = roomReservationService.selectById(id);
        roomReservationEntity = roomReservationService.selectView(new EntityWrapper<RoomReservationEntity>().eq("id", id));
        return R.ok().put("data", roomReservationEntity);
    }

    @RequestMapping("/save")
    public R save(@RequestBody RoomReservationEntity roomReservationEntity, HttpServletRequest request){
        roomReservationEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        roomReservationService.insert(roomReservationEntity);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody RoomReservationEntity roomReservationEntity, HttpServletRequest request){
        roomReservationEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        roomReservationService.insert(roomReservationEntity);
        return R.ok();
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody RoomReservationEntity roomReservationEntity, HttpServletRequest request){
        roomReservationService.updateById(roomReservationEntity);
        return R.ok();
    }

    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String isApproved, @RequestParam String reviewRespone){
        List<RoomReservationEntity> list = new ArrayList<RoomReservationEntity>();
        for(Long id : ids) {
            RoomReservationEntity roomReservationEntity = roomReservationService.selectById(id);
            roomReservationEntity.setIsApproved(isApproved);
            roomReservationEntity.setReviewResponse(reviewRespone);
            list.add(roomReservationEntity);
        }
        roomReservationService.updateBatchById(list);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        roomReservationService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<RoomReservationEntity> ew = new EntityWrapper<RoomReservationEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            ew.eq("userAccount", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = roomReservationService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<RoomReservationEntity> ew = new EntityWrapper<RoomReservationEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            ew.eq("userAccount", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = roomReservationService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        EntityWrapper<RoomReservationEntity> ew = new EntityWrapper<RoomReservationEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            ew.eq("userAccount", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = roomReservationService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<RoomReservationEntity> ew = new EntityWrapper<RoomReservationEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            ew.eq("userAccount", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = roomReservationService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        EntityWrapper<RoomReservationEntity> ew = new EntityWrapper<RoomReservationEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            ew.eq("userAccount", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = roomReservationService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,RoomReservationEntity roomReservationEntity, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            roomReservationEntity.setUserAccount((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<RoomReservationEntity> ew = new EntityWrapper<RoomReservationEntity>();
        int count = roomReservationService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, roomReservationEntity), params), params));
        return R.ok().put("data", count);
    }

}
