package com.salesmanagement.demo.entity;

import java.util.Date;

import lombok.Data;

@Data
public class LoginUser {
	private String userId;
	private String password;
	private int admin;
	private int errCount;
	private int lockFlg;
	private Date createDt;
	private Date updateDt;
	private Date deleteDt;
}
