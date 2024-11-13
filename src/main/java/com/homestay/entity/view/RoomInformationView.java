package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.RoomInformationEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("roominformation")
public class RoomInformationView extends RoomInformationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public RoomInformationView(){
    }

    public RoomInformationView(RoomInformationEntity roomInformationEntity){
        try {
            BeanUtils.copyProperties(this, roomInformationEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
