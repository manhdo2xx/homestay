package com.homestay.controller;

import com.baidu.aip.face.AipFace;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.service.CommonService;
import com.homestay.service.ConfigService;
import com.homestay.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class CommonController{
    @Autowired
    private CommonService commonService;

    private static AipFace client = null;

    @Autowired
    private ConfigService configService;

    @IgnoreAuth
    @RequestMapping("/option/{tableName}/{columnName}")
    public R getOption(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, String level, String parent) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", tableName);
        params.put("column", columnName);
        if(StringUtils.isNotBlank(level)) {
            params.put("level", level);
        }
        if(StringUtils.isNotBlank(parent)) {
            params.put("parent", parent);
        }
        if(StringUtils.isNotBlank(conditionColumn)) {
            params.put("conditionColumn", conditionColumn);
        }
        if(StringUtils.isNotBlank(conditionValue)) {
            params.put("conditionValue", conditionValue);
        }
        List<String> data = commonService.getOption(params);
        return R.ok().put("data", data);
    }

    @IgnoreAuth
    @RequestMapping("/follow/{tableName}/{columnName}")
    public R getFollowByOption(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName, @RequestParam String columnValue) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", tableName);
        params.put("column", columnName);
        params.put("columnValue", columnValue);
        Map<String, Object> result = commonService.getFollowByOption(params);
        return R.ok().put("data", result);
    }

    @RequestMapping("/sh/{tableName}")
    public R sh(@PathVariable("tableName") String tableName, @RequestBody Map<String, Object> map) {
        map.put("table", tableName);
        commonService.sh(map);
        return R.ok();
    }

    @IgnoreAuth
    @RequestMapping("/remind/{tableName}/{columnName}/{type}")
    public R remindCount(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName,
                         @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
        map.put("table", tableName);
        map.put("column", columnName);
        map.put("type", type);

        if(type.equals("2")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Date remindStartDate = null;
            Date remindEndDate = null;
            if(map.get("remindstart")!=null) {
                Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH,remindStart);
                remindStartDate = c.getTime();
                map.put("remindstart", sdf.format(remindStartDate));
            }
            if(map.get("remindend")!=null) {
                Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH,remindEnd);
                remindEndDate = c.getTime();
                map.put("remindend", sdf.format(remindEndDate));
            }
        }

        int count = commonService.remindCount(map);
        return R.ok().put("count", count);
    }

    @IgnoreAuth
    @RequestMapping("/cal/{tableName}/{columnName}")
    public R cal(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", tableName);
        params.put("column", columnName);
        Map<String, Object> result = commonService.selectCal(params);
        return R.ok().put("data", result);
    }

    @IgnoreAuth
    @RequestMapping("/group/{tableName}/{columnName}")
    public R group(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", tableName);
        params.put("column", columnName);
        List<Map<String, Object>> result = commonService.selectGroup(params);
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

    @IgnoreAuth
    @RequestMapping("/value/{tableName}/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("tableName") String tableName, @PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", tableName);
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        List<Map<String, Object>> result = commonService.selectValue(params);
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

    @IgnoreAuth
    @RequestMapping("/value/{tableName}/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("tableName") String tableName, @PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", tableName);
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        List<Map<String, Object>> result = commonService.selectTimeStatValue(params);
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
}
