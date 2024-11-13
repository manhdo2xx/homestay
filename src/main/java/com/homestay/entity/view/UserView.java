package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.UserEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("user")
public class UserView extends UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public UserView(){
    }

    public UserView(UserEntity userEntity){
        try {
            BeanUtils.copyProperties(this, userEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
