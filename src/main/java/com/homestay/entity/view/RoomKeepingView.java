package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.RoomKeepingEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("roomkeeping")
public class RoomKeepingView extends RoomKeepingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public RoomKeepingView(){
    }

    public RoomKeepingView(RoomKeepingEntity roomKeepingEntity){
        try {
            BeanUtils.copyProperties(this, roomKeepingEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
