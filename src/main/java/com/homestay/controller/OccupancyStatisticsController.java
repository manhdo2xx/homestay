package com.homestay.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.OccupancyStatisticsEntity;
import com.homestay.entity.view.OccupancyStatisticsView;
import com.homestay.service.OccupancyStatisticsService;
import com.homestay.utils.MPUtil;
import com.homestay.utils.PageUtils;
import com.homestay.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/occupancystatistics")
public class OccupancyStatisticsController {
    @Autowired
    private OccupancyStatisticsService occupancyStatisticsService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, OccupancyStatisticsEntity occupancyStatisticsEntity,
                  @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date riqistart,
                  @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date riqiend,
                  HttpServletRequest request){
        EntityWrapper<OccupancyStatisticsEntity> ew = new EntityWrapper<OccupancyStatisticsEntity>();
        if(riqistart!=null) ew.ge("date", riqistart);
        if(riqiend!=null) ew.le("date", riqiend);

        PageUtils page = occupancyStatisticsService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, occupancyStatisticsEntity), params), params));

        return R.ok().put("data", page);
    }

    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,OccupancyStatisticsEntity occupancyStatisticsEntity,
                  @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date riqistart,
                  @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date riqiend,
                  HttpServletRequest request){
        EntityWrapper<OccupancyStatisticsEntity> ew = new EntityWrapper<OccupancyStatisticsEntity>();
        if(riqistart!=null) ew.ge("date", riqistart);
        if(riqiend!=null) ew.le("date", riqiend);

        PageUtils page = occupancyStatisticsService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, occupancyStatisticsEntity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R list( OccupancyStatisticsEntity occupancyStatisticsEntity){
        EntityWrapper<OccupancyStatisticsEntity> ew = new EntityWrapper<OccupancyStatisticsEntity>();
        ew.allEq(MPUtil.allEQMapPre( occupancyStatisticsEntity, "occupancystatistics"));
        return R.ok().put("data", occupancyStatisticsService.selectListView(ew));
    }

    @RequestMapping("/query")
    public R query(OccupancyStatisticsEntity ruzhulvtongji){
        EntityWrapper< OccupancyStatisticsEntity> ew = new EntityWrapper< OccupancyStatisticsEntity>();
        ew.allEq(MPUtil.allEQMapPre( ruzhulvtongji, "occupancystatistics"));
        OccupancyStatisticsView ruzhulvtongjiView =  occupancyStatisticsService.selectView(ew);
        return R.ok("Query occupancy rate statistics successfully").put("data", ruzhulvtongjiView);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        OccupancyStatisticsEntity occupancyStatisticsEntity = occupancyStatisticsService.selectById(id);
        occupancyStatisticsEntity = occupancyStatisticsService.selectView(new EntityWrapper<OccupancyStatisticsEntity>().eq("id", id));
        return R.ok().put("data", occupancyStatisticsEntity);
    }

    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        OccupancyStatisticsEntity occupancyStatisticsEntity = occupancyStatisticsService.selectById(id);
        occupancyStatisticsEntity = occupancyStatisticsService.selectView(new EntityWrapper<OccupancyStatisticsEntity>().eq("id", id));
        return R.ok().put("data", occupancyStatisticsEntity);
    }

    @RequestMapping("/save")
    public R save(@RequestBody OccupancyStatisticsEntity occupancyStatisticsEntity, HttpServletRequest request){
        occupancyStatisticsEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        occupancyStatisticsService.insert(occupancyStatisticsEntity);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody OccupancyStatisticsEntity occupancyStatisticsEntity, HttpServletRequest request){
        occupancyStatisticsEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        occupancyStatisticsService.insert(occupancyStatisticsEntity);
        return R.ok();
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody OccupancyStatisticsEntity occupancyStatisticsEntity, HttpServletRequest request){
        occupancyStatisticsService.updateById(occupancyStatisticsEntity);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        occupancyStatisticsService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<OccupancyStatisticsEntity> ew = new EntityWrapper<OccupancyStatisticsEntity>();
        List<Map<String, Object>> result = occupancyStatisticsService.selectValue(params, ew);
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
        EntityWrapper<OccupancyStatisticsEntity> ew = new EntityWrapper<OccupancyStatisticsEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = occupancyStatisticsService.selectValue(params, ew);
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
        EntityWrapper<OccupancyStatisticsEntity> ew = new EntityWrapper<OccupancyStatisticsEntity>();
        List<Map<String, Object>> result = occupancyStatisticsService.selectTimeStatValue(params, ew);
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
        EntityWrapper<OccupancyStatisticsEntity> ew = new EntityWrapper<OccupancyStatisticsEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = occupancyStatisticsService.selectTimeStatValue(params, ew);
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
        EntityWrapper<OccupancyStatisticsEntity> ew = new EntityWrapper<OccupancyStatisticsEntity>();
        List<Map<String, Object>> result = occupancyStatisticsService.selectGroup(params, ew);
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
    public R count(@RequestParam Map<String, Object> params,OccupancyStatisticsEntity occupancyStatisticsEntity, HttpServletRequest request){
        EntityWrapper<OccupancyStatisticsEntity> ew = new EntityWrapper<OccupancyStatisticsEntity>();
        int count = occupancyStatisticsService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, occupancyStatisticsEntity), params), params));
        return R.ok().put("data", count);
    }

}
