package com.salesmanagement.demo.form;

import java.util.List;

import com.salesmanagement.demo.entity.Stock;

import lombok.Data;

@Data
public class StockListForm {
	private String stFormStId;
	private String stFormMid;
	private String stFormInventoryDateFrom;
	private String stFormInventoryDateTo;
	private String stFormInventoryPoint;
	private String stFormCreateDateFrom;
	private String stFormCreateDateTo;
	private String stFormUpdateDateFrom;
	private String stFormUpdateDatTo;
	private List<Stock> stList;
}
