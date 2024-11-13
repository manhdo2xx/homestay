package com.homestay.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.homestay.entity.OccupancyStatisticsEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("occupancystatistics")
public class OccupancyStatisticsView extends OccupancyStatisticsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public OccupancyStatisticsView(){
    }

    public OccupancyStatisticsView(OccupancyStatisticsEntity occupancyStatisticsEntity){
        try {
            BeanUtils.copyProperties(this, occupancyStatisticsEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
