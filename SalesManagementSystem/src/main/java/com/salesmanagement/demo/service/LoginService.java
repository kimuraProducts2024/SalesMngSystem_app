package com.salesmanagement.demo.service;

import com.salesmanagement.demo.entity.LoginUser;

public interface LoginService {

	// ユーザ情報取得
	public LoginUser selectLoginUser(String lFormUserId);
	
	// エラーカウントの更新
	public void updateErrCount(String lFormUserId, int lFormErrcount);
	
	// パスワード変更処理
	public void updatePassword(String lFormUserId, String lFormPassword);
	
	// ユーザ登録
	public void inserLoginUser(String lFormUserId, String lFormPassword);
}
