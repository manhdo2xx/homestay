package com.homestay.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.ComplaintSuggestionEntity;
import com.homestay.entity.view.ComplaintSuggestionView;
import com.homestay.service.ComplaintSuggestionService;
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
@RequestMapping("/complaintsuggestion")
public class ComplaintSuggestionController {
    @Autowired
    private ComplaintSuggestionService complaintSuggestionService;
    
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, ComplaintSuggestionEntity complaintSuggestionEntity,
                  HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("user")) {
            complaintSuggestionEntity.setUserAccount((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<ComplaintSuggestionEntity> ew = new EntityWrapper<ComplaintSuggestionEntity>();

        PageUtils page = complaintSuggestionService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, complaintSuggestionEntity), params), params));

        return R.ok().put("data", page);
    }

    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ComplaintSuggestionEntity complaintSuggestionEntity,
                  HttpServletRequest request){
        EntityWrapper<ComplaintSuggestionEntity> ew = new EntityWrapper<ComplaintSuggestionEntity>();

        PageUtils page = complaintSuggestionService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, complaintSuggestionEntity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R list( ComplaintSuggestionEntity complaintSuggestionEntity){
        EntityWrapper<ComplaintSuggestionEntity> ew = new EntityWrapper<ComplaintSuggestionEntity>();
        ew.allEq(MPUtil.allEQMapPre( complaintSuggestionEntity, "tousujianyi"));
        return R.ok().put("data", complaintSuggestionService.selectListView(ew));
    }

    @RequestMapping("/query")
    public R query(ComplaintSuggestionEntity complaintSuggestionEntity){
        EntityWrapper< ComplaintSuggestionEntity> ew = new EntityWrapper< ComplaintSuggestionEntity>();
        ew.allEq(MPUtil.allEQMapPre( complaintSuggestionEntity, "tousujianyi"));
        ComplaintSuggestionView complaintSuggestionView =  complaintSuggestionService.selectView(ew);
        return R.ok("Inquiry, complaint and suggestion successful").put("data", complaintSuggestionView);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ComplaintSuggestionEntity complaintSuggestionEntity = complaintSuggestionService.selectById(id);
        complaintSuggestionEntity = complaintSuggestionService.selectView(new EntityWrapper<ComplaintSuggestionEntity>().eq("id", id));
        return R.ok().put("data", complaintSuggestionEntity);
    }

    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ComplaintSuggestionEntity complaintSuggestionEntity = complaintSuggestionService.selectById(id);
        complaintSuggestionEntity = complaintSuggestionService.selectView(new EntityWrapper<ComplaintSuggestionEntity>().eq("id", id));
        return R.ok().put("data", complaintSuggestionEntity);
    }

    @RequestMapping("/save")
    public R save(@RequestBody ComplaintSuggestionEntity complaintSuggestionEntity, HttpServletRequest request){
        complaintSuggestionEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        complaintSuggestionService.insert(complaintSuggestionEntity);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody ComplaintSuggestionEntity complaintSuggestionEntity, HttpServletRequest request){
        complaintSuggestionEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        complaintSuggestionService.insert(complaintSuggestionEntity);
        return R.ok();
    }


    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ComplaintSuggestionEntity complaintSuggestionEntity, HttpServletRequest request){
        complaintSuggestionService.updateById(complaintSuggestionEntity);
        return R.ok();
    }


    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        complaintSuggestionService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
