package com.salesmanagement.demo.form;

import lombok.Data;

@Data
public class LoginForm {
	private String lFormUserid;
	private String lFormPassword;
	private String lFormConfpassword;
	private String lFormErrcount;
	private String lFormMessage;
	private String lFormErrMsg;
}
