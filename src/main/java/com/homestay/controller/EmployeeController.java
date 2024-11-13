package com.homestay.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.EmployeeEntity;
import com.homestay.entity.view.EmployeeView;
import com.homestay.service.EmployeeService;
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
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;



    
	@Autowired
	private TokenService tokenService;

	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
            EmployeeEntity u = employeeService.selectOne(new EntityWrapper<EmployeeEntity>().eq("employeeIdNumber", username));
        if(u==null || !u.getEmployeePassword().equals(password)) {
            return R.error("The account or password is incorrect");
        }
		String token = tokenService.generateToken(u.getId(), username,"employee",  "employee" );
		return R.ok().put("token", token);
	}
    
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody EmployeeEntity employee){
    	EmployeeEntity u = employeeService.selectOne(new EntityWrapper<EmployeeEntity>().eq("employeeIdNumber", employee.getEmployeeIdNumber()));
		if(u!=null) {
			return R.error("Registered user already exists");
		}
		Long uId = new Date().getTime();
		employee.setId(uId);
        employeeService.insert(employee);
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
        EmployeeEntity u = employeeService.selectById(id);
        return R.ok().put("data", u);
    }

    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	EmployeeEntity u = employeeService.selectOne(new EntityWrapper<EmployeeEntity>().eq("employeeIdNumber", username));
    	if(u==null) {
    		return R.error("Account does not exist");
    	}
        u.setEmployeePassword("123456");
        employeeService.updateById(u);
        return R.ok("Password has been reset to：123456");
    }

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,EmployeeEntity employee,
		HttpServletRequest request){
        EntityWrapper<EmployeeEntity> ew = new EntityWrapper<EmployeeEntity>();

		PageUtils page = employeeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, employee), params), params));

        return R.ok().put("data", page);
    }

	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,EmployeeEntity employee, HttpServletRequest request){
        EntityWrapper<EmployeeEntity> ew = new EntityWrapper<EmployeeEntity>();

		PageUtils page = employeeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, employee), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R list( EmployeeEntity employee){
       	EntityWrapper<EmployeeEntity> ew = new EntityWrapper<EmployeeEntity>();
      	ew.allEq(MPUtil.allEQMapPre( employee, "employee")); 
        return R.ok().put("data", employeeService.selectListView(ew));
    }

    @RequestMapping("/query")
    public R query(EmployeeEntity employee){
        EntityWrapper< EmployeeEntity> ew = new EntityWrapper< EmployeeEntity>();
 		ew.allEq(MPUtil.allEQMapPre( employee, "employee")); 
		EmployeeView employeeView =  employeeService.selectView(ew);
		return R.ok("Query employee successfully").put("data", employeeView);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        EmployeeEntity employee = employeeService.selectById(id);
		employee = employeeService.selectView(new EntityWrapper<EmployeeEntity>().eq("id", id));
        return R.ok().put("data", employee);
    }

	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        EmployeeEntity employee = employeeService.selectById(id);
		employee = employeeService.selectView(new EntityWrapper<EmployeeEntity>().eq("id", id));
        return R.ok().put("data", employee);
    }

    @RequestMapping("/save")
    public R save(@RequestBody EmployeeEntity employee, HttpServletRequest request){
    	employee.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	EmployeeEntity u = employeeService.selectOne(new EntityWrapper<EmployeeEntity>().eq("employeeIdNumber", employee.getEmployeeIdNumber()));
		if(u!=null) {
			return R.error("User already exists");
		}
		employee.setId(new Date().getTime());
        employeeService.insert(employee);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody EmployeeEntity employee, HttpServletRequest request){
    	employee.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	EmployeeEntity u = employeeService.selectOne(new EntityWrapper<EmployeeEntity>().eq("employeeIdNumber", employee.getEmployeeIdNumber()));
		if(u!=null) {
			return R.error("User already exists");
		}
		employee.setId(new Date().getTime());
        employeeService.insert(employee);
        return R.ok();
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody EmployeeEntity employee, HttpServletRequest request){
        employeeService.updateById(employee);//全部更新
        return R.ok();
    }


    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        employeeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
