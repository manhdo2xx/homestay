package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.MenuEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("menu")
public class MenuView extends MenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public MenuView(){
    }

    public MenuView(MenuEntity menuEntity){
        try {
            BeanUtils.copyProperties(this, menuEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
