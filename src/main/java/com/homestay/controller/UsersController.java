
package com.homestay.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.UsersEntity;
import com.homestay.service.TokenService;
import com.homestay.service.UsersService;
import com.homestay.utils.MPUtil;
import com.homestay.utils.PageUtils;
import com.homestay.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;


@RequestMapping("users")
@RestController
public class UsersController {
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private TokenService tokenService;


	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		UsersEntity user = userService.selectOne(new EntityWrapper<UsersEntity>().eq("userName", username));
		if(user==null || !user.getPassword().equals(password)) {
			return R.error("The account or password is incorrect");
		}
		String token = tokenService.generateToken(user.getId(),username, "users", user.getRole());
		return R.ok().put("token", token);
	}
	

	@IgnoreAuth
	@PostMapping(value = "/register")
	public R register(@RequestBody UsersEntity user){
    	if(userService.selectOne(new EntityWrapper<UsersEntity>().eq("userName", user.getUsername())) !=null) {
    		return R.error("User already exists");
    	}
        userService.insert(user);
        return R.ok();
    }

	@GetMapping(value = "logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("Exit successfully");
	}

    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	UsersEntity user = userService.selectOne(new EntityWrapper<UsersEntity>().eq("userName", username));
    	if(user==null) {
    		return R.error("Account does not exist");
    	}
    	user.setPassword("123456");
        userService.update(user,null);
        return R.ok("Password has been reset to：123456");
    }

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,UsersEntity user){
        EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();
    	PageUtils page = userService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, user), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/list")
    public R list( UsersEntity user){
       	EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();
      	ew.allEq(MPUtil.allEQMapPre( user, "user")); 
        return R.ok().put("data", userService.selectListView(ew));
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        UsersEntity user = userService.selectById(id);
        return R.ok().put("data", user);
    }

    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        UsersEntity user = userService.selectById(id);
        return R.ok().put("data", user);
    }

    @PostMapping("/save")
    public R save(@RequestBody UsersEntity user){
    	if(userService.selectOne(new EntityWrapper<UsersEntity>().eq("userName", user.getUsername())) !=null) {
    		return R.error("User already exists");
    	}
        userService.insert(user);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody UsersEntity user){
    	UsersEntity u = userService.selectOne(new EntityWrapper<UsersEntity>().eq("userName", user.getUsername()));
    	if(u!=null && u.getId()!=user.getId() && u.getUsername().equals(user.getUsername())) {
    		return R.error("Username already exists。");
    	}
        userService.updateById(user);//全部更新
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        userService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
