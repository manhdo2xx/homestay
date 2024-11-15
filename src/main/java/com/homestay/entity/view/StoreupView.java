package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.StoreupEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("storeup")
public class StoreupView  extends StoreupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public StoreupView(){
    }

    public StoreupView(StoreupEntity storeupEntity){
        try {
            BeanUtils.copyProperties(this, storeupEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}