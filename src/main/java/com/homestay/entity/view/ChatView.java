package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.ChatEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("chat")
public class ChatView  extends ChatEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public ChatView(){
    }

    public ChatView(ChatEntity chatEntity){
        try {
            BeanUtils.copyProperties(this, chatEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
