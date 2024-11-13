package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.EmployeeEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("employee")
public class EmployeeView extends EmployeeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public EmployeeView(){
    }

    public EmployeeView(EmployeeEntity employeeEntity){
        try {
            BeanUtils.copyProperties(this, employeeEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
