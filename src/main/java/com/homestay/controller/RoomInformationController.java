package com.homestay.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.entity.RoomInformationEntity;
import com.homestay.entity.StoreupEntity;
import com.homestay.entity.view.RoomInformationView;
import com.homestay.service.RoomInformationService;
import com.homestay.service.StoreupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.homestay.utils.MPUtil;
import com.homestay.utils.PageUtils;
import com.homestay.utils.R;
import com.homestay.annotation.IgnoreAuth;


import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/roominformation")
public class RoomInformationController {
    @Autowired
    private RoomInformationService roomInformationService;

    @Autowired
    private StoreupService storeupService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, RoomInformationEntity roomInformationEntity,
                  @RequestParam(required = false) Double jiagestart,
                  @RequestParam(required = false) Double jiageend,
                  HttpServletRequest request){
        EntityWrapper<RoomInformationEntity> ew = new EntityWrapper<RoomInformationEntity>();
        if(jiagestart!=null) ew.ge("price", jiagestart);
        if(jiageend!=null) ew.le("price", jiageend);
        PageUtils page = roomInformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, roomInformationEntity), params), params));
        return R.ok().put("data", page);
    }

    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,RoomInformationEntity roomInformationEntity,
                  @RequestParam(required = false) Double jiagestart,
                  @RequestParam(required = false) Double jiageend,
                  HttpServletRequest request){
        EntityWrapper<RoomInformationEntity> ew = new EntityWrapper<RoomInformationEntity>();
        if(jiagestart!=null) ew.ge("price", jiagestart);
        if(jiageend!=null) ew.le("price", jiageend);
        PageUtils page = roomInformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, roomInformationEntity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R list( RoomInformationEntity kefangxinxi){
        EntityWrapper<RoomInformationEntity> ew = new EntityWrapper<RoomInformationEntity>();
        ew.allEq(MPUtil.allEQMapPre( kefangxinxi, "roominformation"));
        return R.ok().put("data", roomInformationService.selectListView(ew));
    }

    @RequestMapping("/query")
    public R query(RoomInformationEntity roomInformationEntity){
        EntityWrapper< RoomInformationEntity> ew = new EntityWrapper< RoomInformationEntity>();
        ew.allEq(MPUtil.allEQMapPre( roomInformationEntity, "roominformation"));
        RoomInformationView roomInformationView =  roomInformationService.selectView(ew);
        return R.ok("Query room information successfully").put("data", roomInformationView);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RoomInformationEntity roomInformationEntity = roomInformationService.selectById(id);
        roomInformationEntity.setClicknum(roomInformationEntity.getClicknum()+1);
        roomInformationEntity.setClicktime(new Date());
        roomInformationService.updateById(roomInformationEntity);
        roomInformationEntity  = roomInformationService.selectView(new EntityWrapper<RoomInformationEntity>().eq("id", id));
        return R.ok().put("data", roomInformationEntity);
    }

    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        RoomInformationEntity roomInformationEntity = roomInformationService.selectById(id);
        roomInformationEntity.setClicknum(roomInformationEntity.getClicknum()+1);
        roomInformationEntity.setClicktime(new Date());
        roomInformationService.updateById(roomInformationEntity);
        roomInformationEntity = roomInformationService.selectView(new EntityWrapper<RoomInformationEntity>().eq("id", id));
        return R.ok().put("data", roomInformationEntity);
    }

    @RequestMapping("/save")
    public R save(@RequestBody RoomInformationEntity roomInformationEntity, HttpServletRequest request){
        roomInformationEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        roomInformationService.insert(roomInformationEntity);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody RoomInformationEntity roomInformationEntity, HttpServletRequest request){
        roomInformationEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        roomInformationService.insert(roomInformationEntity);
        return R.ok();
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody RoomInformationEntity roomInformationEntity, HttpServletRequest request){
        roomInformationService.updateById(roomInformationEntity);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        roomInformationService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,RoomInformationEntity roomInformationEntity, HttpServletRequest request,String pre){
        EntityWrapper<RoomInformationEntity> ew = new EntityWrapper<RoomInformationEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            String newKey = entry.getKey();
            if (pre.endsWith(".")) {
                newMap.put(pre + newKey, entry.getValue());
            } else if (StringUtils.isEmpty(pre)) {
                newMap.put(newKey, entry.getValue());
            } else {
                newMap.put(pre + "." + newKey, entry.getValue());
            }
        }
        params.put("sort", "clicknum");
        params.put("order", "desc");
        PageUtils page = roomInformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, roomInformationEntity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,RoomInformationEntity roomInformationEntity, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String inteltypeColumn = "roomType";
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("userid", userId).eq("tablename", "roominformation").orderBy("addtime", false));
        List<String> inteltypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<RoomInformationEntity> kefangxinxiList = new ArrayList<RoomInformationEntity>();
        if(storeups!=null && storeups.size()>0) {
            for(StoreupEntity s : storeups) {
                kefangxinxiList.addAll(roomInformationService.selectList(new EntityWrapper<RoomInformationEntity>().eq(inteltypeColumn, s.getInteltype())));
            }
        }
        EntityWrapper<RoomInformationEntity> ew = new EntityWrapper<RoomInformationEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        PageUtils page = roomInformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, roomInformationEntity), params), params));
        List<RoomInformationEntity> pageList = (List<RoomInformationEntity>)page.getList();
        if(kefangxinxiList.size()<limit) {
            int toAddNum = (limit-kefangxinxiList.size())<=pageList.size()?(limit-kefangxinxiList.size()):pageList.size();
            for(RoomInformationEntity o1 : pageList) {
                boolean addFlag = true;
                for(RoomInformationEntity o2 : kefangxinxiList) {
                    if(o1.getId().intValue()==o2.getId().intValue()) {
                        addFlag = false;
                        break;
                    }
                }
                if(addFlag) {
                    kefangxinxiList.add(o1);
                    if(--toAddNum==0) break;
                }
            }
        } else if(kefangxinxiList.size()>limit) {
            kefangxinxiList = kefangxinxiList.subList(0, limit);
        }
        page.setList(kefangxinxiList);
        return R.ok().put("data", page);
    }
    
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<RoomInformationEntity> ew = new EntityWrapper<RoomInformationEntity>();
        List<Map<String, Object>> result = roomInformationService.selectValue(params, ew);
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
        EntityWrapper<RoomInformationEntity> ew = new EntityWrapper<RoomInformationEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = roomInformationService.selectValue(params, ew);
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
        EntityWrapper<RoomInformationEntity> ew = new EntityWrapper<RoomInformationEntity>();
        List<Map<String, Object>> result = roomInformationService.selectTimeStatValue(params, ew);
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
        EntityWrapper<RoomInformationEntity> ew = new EntityWrapper<RoomInformationEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = roomInformationService.selectTimeStatValue(params, ew);
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
        EntityWrapper<RoomInformationEntity> ew = new EntityWrapper<RoomInformationEntity>();
        List<Map<String, Object>> result = roomInformationService.selectGroup(params, ew);
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
    public R count(@RequestParam Map<String, Object> params,RoomInformationEntity roomInformationEntity, HttpServletRequest request){
        EntityWrapper<RoomInformationEntity> ew = new EntityWrapper<RoomInformationEntity>();
        int count = roomInformationService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, roomInformationEntity), params), params));
        return R.ok().put("data", count);
    }

}
