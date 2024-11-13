package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.ComplaintSuggestionEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("complaintsuggestion")
public class ComplaintSuggestionView extends ComplaintSuggestionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public ComplaintSuggestionView(){
    }

    public ComplaintSuggestionView(ComplaintSuggestionEntity complaintSuggestionEntity){
        try {
            BeanUtils.copyProperties(this, complaintSuggestionEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
