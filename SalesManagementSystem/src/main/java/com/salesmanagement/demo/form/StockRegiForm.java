package com.salesmanagement.demo.form;

import lombok.Data;

@Data
public class StockRegiForm {
	private String stFormStId;
	private String stFormMid;
	private String stFormInventoryDate;
	private String stFormInventoryNum;
	private String stFormMaxStockNum;
	private String stFormOrderPoint;
	private String stFormInventoryPoint;
	private int stFormStatus;
}
