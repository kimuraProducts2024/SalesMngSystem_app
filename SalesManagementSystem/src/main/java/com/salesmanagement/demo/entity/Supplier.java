package com.salesmanagement.demo.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Supplier implements Serializable {
	private int supid;
	private String supname;
	private Date createdate;
	private Date updatedate;
	private int delflg;
}
