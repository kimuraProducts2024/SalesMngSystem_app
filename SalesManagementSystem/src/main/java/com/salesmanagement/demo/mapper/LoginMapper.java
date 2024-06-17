package com.salesmanagement.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.salesmanagement.demo.entity.LoginUser;

@Mapper
public interface LoginMapper {
	
	// ユーザ情報取得
	public LoginUser selectLoginUser(String lFormUserId);
	
	// エラーカウントの更新
	public void updateErrCount(String lFormUserId, int lFormErrcount);
	
	// パスワード変更処理
	public void updatePassword(String lFormUserId, String lFormPassword);
	
	// ユーザ登録
	public void insertLoginUser(String lFormUserId, String lFormPassword);
	
}
