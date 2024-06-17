package com.salesmanagement.demo.service;

import java.util.List;

import com.salesmanagement.demo.entity.Supplier;

public interface SupplierService {
	// 発注先全データを検索
	public List<Supplier> getAllSupplier();
	
	// 検索画面の入力値を条件に、テーブルを検索
	public List<Supplier> searchSupplier(String mFormSupId, String mFormSupName, 
													String mFormCreateDateFrom, String mFormCreateDateTo, 
													String mFormUpdateDateFrom, String mFormUpdateDateTo);
	
	// 発注先を新規登録
	public void insertSupplier(String sFormSupId, String sFormSupName);
	
	// 発注先情報更新
	public void editSupplier(String sFormSupId, String sFormSupName);
	
	// 商品情報削除
	public void deleteSupplier(String sFormSupId);
}
