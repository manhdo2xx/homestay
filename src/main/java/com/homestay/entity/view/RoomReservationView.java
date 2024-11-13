package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.RoomReservationEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("roomreservation")
public class RoomReservationView extends RoomReservationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public RoomReservationView(){
    }

    public RoomReservationView(RoomReservationEntity roomReservationEntity){
        try {
            BeanUtils.copyProperties(this, roomReservationEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
