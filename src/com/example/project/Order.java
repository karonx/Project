package com.example.project;

import cn.bmob.v3.BmobObject;

public class Order extends BmobObject{
	String start;
	String destination;
	String drivernumber;
	boolean status;
	String apotime;
	String userid;
	String userphone;
	String driverphone;
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getDriverphone() {
		return driverphone;
	}
	public void setDriverphone(String driverphone) {
		this.driverphone = driverphone;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDrivernumber() {
		return drivernumber;
	}
	public void setDrivernumber(String drivernumber) {
		this.drivernumber = drivernumber;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getApotime() {
		return apotime;
	}
	public void setApotime(String apotime) {
		this.apotime = apotime;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
