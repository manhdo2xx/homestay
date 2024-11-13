
package com.homestay.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.ConfigEntity;
import com.homestay.service.ConfigService;
import com.homestay.utils.MPUtil;
import com.homestay.utils.PageUtils;
import com.homestay.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RequestMapping("config")
@RestController
public class ConfigController {
	
	@Autowired
	private ConfigService configService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, ConfigEntity config){
        EntityWrapper<ConfigEntity> ew = new EntityWrapper<ConfigEntity>();
    	PageUtils page = configService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, config), params), params));
        return R.ok().put("data", page);
    }

    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ConfigEntity config){
        EntityWrapper<ConfigEntity> ew = new EntityWrapper<ConfigEntity>();
    	PageUtils page = configService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, config), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        ConfigEntity config = configService.selectById(id);
        return R.ok().put("data", config);
    }

    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") String id){
        ConfigEntity config = configService.selectById(id);
        return R.ok().put("data", config);
    }

    @RequestMapping("/info")
    public R infoByName(@RequestParam String name){
        ConfigEntity config = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "faceFile"));
        return R.ok().put("data", config);
    }

    @PostMapping("/save")
    public R save(@RequestBody ConfigEntity config){
    	configService.insert(config);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody ConfigEntity config){
        configService.updateById(config);//全部更新
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
    	configService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
