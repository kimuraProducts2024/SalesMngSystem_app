package com.salesmanagement.demo.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Merchandise implements Serializable {
	private Integer mid;
	private String mname;
	private int price;
	private int supid;
	private String supname;
	private int supunit;
	private int puckingnum;
	private int delflg;
	private Date createdate;
	private Date updatedate;
}
