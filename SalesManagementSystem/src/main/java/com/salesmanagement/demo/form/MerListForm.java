package com.salesmanagement.demo.form;

import java.util.List;

import com.salesmanagement.demo.entity.Merchandise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerListForm {
	private String mFormid;
	private String mFormname;
	private String mFormcreatedatefrom;
	private String mFormcreatedateto;
	private String mFormupdatedatefrom;
	private String mFormupdatedateto;
	private List<Merchandise> mList;
}
