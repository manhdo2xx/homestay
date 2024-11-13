package com.homestay.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.DiscussRoomInformationEntity;
import com.homestay.entity.view.DiscussRoomInformationView;
import com.homestay.service.DiscussRoomInformationService;
import com.homestay.utils.MPUtil;
import com.homestay.utils.PageUtils;
import com.homestay.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/discussroominformation")
public class DiscussRoomInformationController {
    @Autowired
    private DiscussRoomInformationService discussRoomInformationService;
    
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, DiscussRoomInformationEntity discussRoomInformationEntity,
                  HttpServletRequest request){
        EntityWrapper<DiscussRoomInformationEntity> ew = new EntityWrapper<DiscussRoomInformationEntity>();

        PageUtils page = discussRoomInformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussRoomInformationEntity), params), params));

        return R.ok().put("data", page);
    }

    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussRoomInformationEntity discussRoomInformationEntity,
                  HttpServletRequest request){
        EntityWrapper<DiscussRoomInformationEntity> ew = new EntityWrapper<DiscussRoomInformationEntity>();

        PageUtils page = discussRoomInformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussRoomInformationEntity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R list( DiscussRoomInformationEntity discussRoomInformationEntity){
        EntityWrapper<DiscussRoomInformationEntity> ew = new EntityWrapper<DiscussRoomInformationEntity>();
        ew.allEq(MPUtil.allEQMapPre( discussRoomInformationEntity, "discussRoomInformation"));
        return R.ok().put("data", discussRoomInformationService.selectListView(ew));
    }
    
    @RequestMapping("/query")
    public R query(DiscussRoomInformationEntity discussRoomInformationEntity){
        EntityWrapper< DiscussRoomInformationEntity> ew = new EntityWrapper< DiscussRoomInformationEntity>();
        ew.allEq(MPUtil.allEQMapPre( discussRoomInformationEntity, "discussRoomInformation"));
        DiscussRoomInformationView discussRoomInformationView =  discussRoomInformationService.selectView(ew);
        return R.ok("Query room information comment form successfully").put("data", discussRoomInformationView);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussRoomInformationEntity discussRoomInformationEntity = discussRoomInformationService.selectById(id);
        discussRoomInformationEntity = discussRoomInformationService.selectView(new EntityWrapper<DiscussRoomInformationEntity>().eq("id", id));
        return R.ok().put("data", discussRoomInformationEntity);
    }

    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussRoomInformationEntity discussRoomInformationEntity = discussRoomInformationService.selectById(id);
        discussRoomInformationEntity = discussRoomInformationService.selectView(new EntityWrapper<DiscussRoomInformationEntity>().eq("id", id));
        return R.ok().put("data", discussRoomInformationEntity);
    }

    @RequestMapping("/save")
    public R save(@RequestBody DiscussRoomInformationEntity discussRoomInformationEntity, HttpServletRequest request){
        discussRoomInformationEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        discussRoomInformationService.insert(discussRoomInformationEntity);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody DiscussRoomInformationEntity discussRoomInformationEntity, HttpServletRequest request){
        discussRoomInformationEntity.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        discussRoomInformationService.insert(discussRoomInformationEntity);
        return R.ok();
    }

    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussRoomInformationEntity discussRoomInformationEntity, HttpServletRequest request){
        discussRoomInformationService.updateById(discussRoomInformationEntity);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussRoomInformationService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussRoomInformationEntity discussRoomInformationEntity, HttpServletRequest request,String pre){
        EntityWrapper<DiscussRoomInformationEntity> ew = new EntityWrapper<DiscussRoomInformationEntity>();
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
        params.put("sort", "clicktime");
        params.put("order", "desc");
        PageUtils page = discussRoomInformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussRoomInformationEntity), params), params));
        return R.ok().put("data", page);
    }

}
