package com.homestay.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@TableName("roominformation")
public class RoomInformationEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public RoomInformationEntity() {

    }

    public RoomInformationEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @TableId
    private Long id;

    private String roomNumber;

    private String roomPictures;

    @JsonFormat(locale="vn", timezone="GMT+7", pattern="yyyy-MM-dd")
    @DateTimeFormat
    private Date availableDates;

    private String roomType;

    private String floor;

    private Double price;

    private String roomFacilities;

    private String roomDetails;

    private String roomStatus;

    private Integer storeupnum;

    @JsonFormat(locale="vn", timezone="GMT+7", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date clicktime;

    private Integer clicknum;

    @JsonFormat(locale="vn", timezone="GMT+7", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomPictures() {
        return roomPictures;
    }

    public void setRoomPictures(String roomPictures) {
        this.roomPictures = roomPictures;
    }

    public Date getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(Date availableDates) {
        this.availableDates = availableDates;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRoomFacilities() {
        return roomFacilities;
    }

    public void setRoomFacilities(String roomFacilities) {
        this.roomFacilities = roomFacilities;
    }

    public String getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(String roomDetails) {
        this.roomDetails = roomDetails;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Integer getStoreupnum() {
        return storeupnum;
    }

    public void setStoreupnum(Integer storeupnum) {
        this.storeupnum = storeupnum;
    }

    public Date getClicktime() {
        return clicktime;
    }

    public void setClicktime(Date clicktime) {
        this.clicktime = clicktime;
    }

    public Integer getClicknum() {
        return clicknum;
    }

    public void setClicknum(Integer clicknum) {
        this.clicknum = clicknum;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}
