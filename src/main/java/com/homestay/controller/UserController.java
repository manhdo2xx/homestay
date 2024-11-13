package com.homestay.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.UserEntity;
import com.homestay.entity.view.UserView;
import com.homestay.service.UserService;
import com.homestay.service.TokenService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private TokenService tokenService;
    
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        UserEntity u = userService.selectOne(new EntityWrapper<UserEntity>().eq("userAccount", username));
        if(u==null || !u.getUserPassword().equals(password)) {
            return R.error("The account or password is incorrect");
        }
        String token = tokenService.generateToken(u.getId(), username,"user",  "user" );
        return R.ok().put("token", token);
    }


    @IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody UserEntity user){
        UserEntity u = userService.selectOne(new EntityWrapper<UserEntity>().eq("userAccount", user.getUserAccount()));
        if(u!=null) {
            return R.error("Registered user already exists");
        }
        Long uId = new Date().getTime();
        user.setId(uId);
        userService.insert(user);
        return R.ok();
    }

    @RequestMapping("/logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("Exit successfully");
    }

    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
        Long id = (Long)request.getSession().getAttribute("userId");
        UserEntity u = userService.selectById(id);
        return R.ok().put("data", u);
    }

    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
        UserEntity u = userService.selectOne(new EntityWrapper<UserEntity>().eq("userAccount", username));
        if(u==null) {
            return R.error("Account does not exist");
        }
        u.setUserPassword("123456");
        userService.updateById(u);
        return R.ok("Password has been reset to：123456");
    }
    
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, UserEntity user,
                  HttpServletRequest request){
        EntityWrapper<UserEntity> ew = new EntityWrapper<UserEntity>();

        PageUtils page = userService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, user), params), params));

        return R.ok().put("data", page);
    }
    
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,UserEntity user,
                  HttpServletRequest request){
        EntityWrapper<UserEntity> ew = new EntityWrapper<UserEntity>();

        PageUtils page = userService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, user), params), params));
        return R.ok().put("data", page);
    }
    
    @RequestMapping("/lists")
    public R list( UserEntity user){
        EntityWrapper<UserEntity> ew = new EntityWrapper<UserEntity>();
        ew.allEq(MPUtil.allEQMapPre( user, "user"));
        return R.ok().put("data", userService.selectListView(ew));
    }

    
    @RequestMapping("/query")
    public R query(UserEntity user){
        EntityWrapper< UserEntity> ew = new EntityWrapper< UserEntity>();
        ew.allEq(MPUtil.allEQMapPre( user, "user"));
        UserView userView =  userService.selectView(ew);
        return R.ok("Query user successfully").put("data", userView);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        UserEntity user = userService.selectById(id);
        user = userService.selectView(new EntityWrapper<UserEntity>().eq("id", id));
        return R.ok().put("data", user);
    }

    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        UserEntity user = userService.selectById(id);
        user = userService.selectView(new EntityWrapper<UserEntity>().eq("id", id));
        return R.ok().put("data", user);
    }

    @RequestMapping("/save")
    public R save(@RequestBody UserEntity user, HttpServletRequest request){
        user.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        UserEntity u = userService.selectOne(new EntityWrapper<UserEntity>().eq("userAccount", user.getUserAccount()));
        if(u!=null) {
            return R.error("User already exists");
        }
        user.setId(new Date().getTime());
        userService.insert(user);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody UserEntity user, HttpServletRequest request){
        user.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        UserEntity u = userService.selectOne(new EntityWrapper<UserEntity>().eq("userAccount", user.getUserAccount()));
        if(u!=null) {
            return R.error("User already exists");
        }
        user.setId(new Date().getTime());
        userService.insert(user);
        return R.ok();
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody UserEntity user, HttpServletRequest request){
        userService.updateById(user);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        userService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
