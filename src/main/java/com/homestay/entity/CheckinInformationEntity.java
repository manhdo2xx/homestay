package com.homestay.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@TableName("checkininformation")
public class CheckinInformationEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public CheckinInformationEntity() {

    }

    public CheckinInformationEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @TableId
    private Long id;

    private String bookingNumber;

    private String roomNumber;

    private String roomType;

    @JsonFormat(locale="vn", timezone="GMT+7", pattern="yyyy-MM-dd")
    @DateTimeFormat
    private Date checkinDate;

    private Integer numberDaysStay;

    private Integer numberPeopleStay;

    private String userAccount;

    private String userName;

    private String phoneNumber;

    @JsonFormat(locale="vn", timezone="GMT+7", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date checkinTime;

    private String checkoutStatus;

    private String employeeIdNumber;

    private String employeeName;

    @JsonFormat(locale="vn", timezone="GMT+7", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Integer getNumberDaysStay() {
        return numberDaysStay;
    }

    public void setNumberDaysStay(Integer numberDaysStay) {
        this.numberDaysStay = numberDaysStay;
    }

    public Integer getNumberPeopleStay() {
        return numberPeopleStay;
    }

    public void setNumberPeopleStay(Integer numberPeopleStay) {
        this.numberPeopleStay = numberPeopleStay;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(String checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public String getEmployeeIdNumber() {
        return employeeIdNumber;
    }

    public void setEmployeeIdNumber(String employeeIdNumber) {
        this.employeeIdNumber = employeeIdNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}
