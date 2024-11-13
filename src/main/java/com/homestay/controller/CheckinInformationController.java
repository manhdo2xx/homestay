package com.homestay.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.CheckinInformationEntity;
import com.homestay.entity.view.CheckinInformationView;
import com.homestay.service.CheckinInformationService;
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
@RequestMapping("/checkinInformation")
public class CheckinInformationController {
    @Autowired
    private CheckinInformationService checkinInformationService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, CheckinInformationEntity checkinInformationEntity,
                  HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            checkinInformationEntity.setUserAccount((String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("employee")) {
            checkinInformationEntity.setEmployeeIdNumber((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<CheckinInformationEntity> ew = new EntityWrapper<CheckinInformationEntity>();

        PageUtils page = checkinInformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, checkinInformationEntity), params), params));

        return R.ok().put("data", page);
    }

    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,CheckinInformationEntity checkinInformationEntity,
                  HttpServletRequest request){
        EntityWrapper<CheckinInformationEntity> ew = new EntityWrapper<CheckinInformationEntity>();

        PageUtils page = checkinInformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, checkinInformationEntity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R list( CheckinInformationEntity checkinInformationEntity){
        EntityWrapper<CheckinInformationEntity> ew = new EntityWrapper<CheckinInformationEntity>();
        ew.allEq(MPUtil.allEQMapPre( checkinInformationEntity, "checkinInformationEntity"));
        return R.ok().put("data", checkinInformationService.selectListView(ew));
    }

    @RequestMapping("/query")
    public R query(CheckinInformationEntity checkinInformationEntity){
        EntityWrapper< CheckinInformationEntity> ew = new EntityWrapper< CheckinInformationEntity>();
        ew.allEq(MPUtil.allEQMapPre( checkinInformationEntity, "checkinInformationEntity"));
        CheckinInformationView checkinInformationView =  checkinInformationService.selectView(ew);
        return R.ok("Query check-in information successfully").put("data", checkinInformationView);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CheckinInformationEntity checkinInformationEntity = checkinInformationService.selectById(id);
        checkinInformationEntity = checkinInformationService.selectView(new EntityWrapper<CheckinInformationEntity>().eq("id", id));
        return R.ok().put("data", checkinInformationEntity);
    }

    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CheckinInformationEntity checkinInformationEntity = checkinInformationService.selectById(id);
        checkinInformationEntity = checkinInformationService.selectView(new EntityWrapper<CheckinInformationEntity>().eq("id", id));
        return R.ok().put("data", checkinInformationEntity);
    }

    @RequestMapping("/save")
    public R save(@RequestBody CheckinInformationEntity checkinInformationEntity, HttpServletRequest request){
        checkinInformationEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        checkinInformationService.insert(checkinInformationEntity);
        return R.ok();
    }


    @RequestMapping("/add")
    public R add(@RequestBody CheckinInformationEntity checkinInformationEntity, HttpServletRequest request){
        checkinInformationEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        checkinInformationService.insert(checkinInformationEntity);
        return R.ok();
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CheckinInformationEntity checkinInformationEntity, HttpServletRequest request){
        checkinInformationService.updateById(checkinInformationEntity);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        checkinInformationService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<CheckinInformationEntity> ew = new EntityWrapper<CheckinInformationEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            ew.eq("userAccount", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("employee")) {
            ew.eq("employeeIdNumber", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = checkinInformationService.selectValue(params, ew);
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
        EntityWrapper<CheckinInformationEntity> ew = new EntityWrapper<CheckinInformationEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            ew.eq("userAccount", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("employee")) {
            ew.eq("employeeIdNumber", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = checkinInformationService.selectValue(params, ew);
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
        EntityWrapper<CheckinInformationEntity> ew = new EntityWrapper<CheckinInformationEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            ew.eq("userAccount", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("employee")) {
            ew.eq("employeeIdNumber", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = checkinInformationService.selectTimeStatValue(params, ew);
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

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<CheckinInformationEntity> ew = new EntityWrapper<CheckinInformationEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            ew.eq("userAccount", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("employee")) {
            ew.eq("employeeIdNumber", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = checkinInformationService.selectTimeStatValue(params, ew);
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
        EntityWrapper<CheckinInformationEntity> ew = new EntityWrapper<CheckinInformationEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            ew.eq("userAccount", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("employee")) {
            ew.eq("employeeIdNumber", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = checkinInformationService.selectGroup(params, ew);
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
    public R count(@RequestParam Map<String, Object> params,CheckinInformationEntity checkinInformationEntity, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            checkinInformationEntity.setUserAccount((String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("employee")) {
            checkinInformationEntity.setEmployeeIdNumber((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<CheckinInformationEntity> ew = new EntityWrapper<CheckinInformationEntity>();
        int count = checkinInformationService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, checkinInformationEntity), params), params));
        return R.ok().put("data", count);
    }


}
