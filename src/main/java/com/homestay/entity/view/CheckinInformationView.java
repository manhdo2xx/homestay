package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.CheckinInformationEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("checkininformation")
public class CheckinInformationView extends CheckinInformationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public CheckinInformationView(){
    }

    public CheckinInformationView(CheckinInformationEntity checkinInformationEntity){
        try {
            BeanUtils.copyProperties(this, checkinInformationEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
