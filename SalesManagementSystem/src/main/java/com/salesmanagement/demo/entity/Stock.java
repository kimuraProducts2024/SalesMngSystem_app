package com.salesmanagement.demo.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Stock implements Serializable {
	private int stId;
	private int mId;
	private String mName;
	private Date inventoryDate;
	private int inventoryNum;
	private int maxStockNum;
	private int orderPoint;
	private String inventoryPoint;
	private Date createDate;
	private Date updateDate;
	private int delFlg;
}
