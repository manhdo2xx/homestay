package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.NewsEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("news")
public class NewsView  extends NewsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public NewsView() {
    }

    public NewsView(NewsEntity newsEntity) {
        try {
            BeanUtils.copyProperties(this, newsEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
