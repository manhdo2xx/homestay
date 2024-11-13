package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.CheckoutInformationEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("checkoutinformation")
public class CheckoutInformationView extends CheckoutInformationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public CheckoutInformationView(){
    }

    public CheckoutInformationView(CheckoutInformationEntity checkoutInformationEntity){
        try {
            BeanUtils.copyProperties(this, checkoutInformationEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
