package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.RoomTypeEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("roomtype")
public class RoomTypeView extends RoomTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public RoomTypeView(){
    }

    public RoomTypeView(RoomTypeEntity roomTypeEntity){
        try {
            BeanUtils.copyProperties(this, roomTypeEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
