package com.homestay.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@TableName("chat")
public class ChatEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public ChatEntity() {

    }

    public ChatEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @TableId
    private Long id;

    private Long adminid;

    private String ask;

    private String reply;

    private Integer isreply;

    private Long userid;

    @JsonFormat(locale = "vi", timezone = "GMT+7", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Date getAddtime() {
        return addtime;
    }
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }

    public Long getAdminid() {
        return adminid;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getAsk() {
        return ask;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    public void setIsreply(Integer isreply) {
        this.isreply = isreply;
    }

    public Integer getIsreply() {
        return isreply;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getUserid() {
        return userid;
    }

}
