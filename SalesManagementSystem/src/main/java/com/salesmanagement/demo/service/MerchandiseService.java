package com.salesmanagement.demo.service;

import java.util.List;

import com.salesmanagement.demo.entity.Merchandise;

public interface MerchandiseService {
	
	// 商品検索
	public Merchandise getMerchandise(int mid);
	
	// 全商品検索
	public List<Merchandise> getAllMerchandise();
	
	// 商品テーブル、発注先テーブルからデータ取得
	public List<Merchandise> getAllMerchanSupp();
	
	// 検索画面の入力値を条件に、テーブルを検索
	public List<Merchandise> getSearchMerchanSupp(String mFormid, String mFormname, 
													String mFormcreatedatefrom, String mFormcreatedateto, 
													String mFormupdatedatefrom, String mFormupdatedateto);
	
	// 商品を新規登録
	public void registMerchandise(String mFormId, String mFormName, String mFormPrice,
			String mFormSupId, String mFormSupUnit, String mFormPuckingNum);

	// 商品情報更新
	public void editMerchandise(String mFormId, String mFormName, String mFormPrice,
			String mFormSupId, String mFormSupUnit, String mFormPuckingNum);
	
	// 商品削除
	public void deleteMerchandise(String mFormId);
}
