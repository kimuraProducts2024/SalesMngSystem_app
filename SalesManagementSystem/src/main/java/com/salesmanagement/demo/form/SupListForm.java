package com.salesmanagement.demo.form;

import java.util.List;

import com.salesmanagement.demo.entity.Supplier;

import lombok.Data;

@Data
public class SupListForm {
	private String sFormSupId;
	private String sFormSupName;
	private String sFormCreateDateFrom;
	private String sFormCreateDateTo;
	private String sFormUpdateDateFrom;
	private String sFormUpdateDateTo;
	private List<Supplier> sList;
	private int sFormStatus;
}
