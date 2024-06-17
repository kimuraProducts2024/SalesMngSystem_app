package com.salesmanagement.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanagement.demo.entity.LoginUser;
import com.salesmanagement.demo.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginMapper mapper;
	
	@Override
	// ユーザ情報取得
	public LoginUser selectLoginUser(String lFormUserId) {
		return mapper.selectLoginUser(lFormUserId);
	}
	
	@Override
	// エラーカウントの更新
	public void updateErrCount(String lFormUserId, int lFormErrcount) {
		mapper.updateErrCount(lFormUserId, lFormErrcount + 1);
	}
	
	// パスワード変更処理
	@Override
	public void updatePassword(String lFormUserId, String lFormPassword) {
		mapper.updatePassword(lFormUserId, lFormPassword);
	}
	
	// ユーザ登録
	@Override
	public void inserLoginUser(String lFormUserId, String lFormPassword) {
		mapper.insertLoginUser(lFormUserId, lFormPassword);
	}
}
