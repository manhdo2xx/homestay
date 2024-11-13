package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.DiscussRoomInformationEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("discussroominformation")
public class DiscussRoomInformationView extends DiscussRoomInformationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public DiscussRoomInformationView(){
    }

    public DiscussRoomInformationView(DiscussRoomInformationEntity discussRoomInfomationEntity){
        try {
            BeanUtils.copyProperties(this, discussRoomInfomationEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
