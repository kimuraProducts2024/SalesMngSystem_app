package com.salesmanagement.demo.service;

import java.util.List;

import com.salesmanagement.demo.entity.Stock;

public interface StockService {
	
	// 商品在庫をIDから検索
	public Stock findById(String stFormStId);
	
	// 商品在庫テーブル、商品テーブルからデータ取得
	public List<Stock> getAllStockMerchan();
	
	// 検索画面の入力値を条件に、テーブルを検索
	public List<Stock> getSearchStockMerchan(String stFormStId, String stFormMid, 
													String stFormInventoryDateFrom, String stFormInventoryDateTo, 
													String stFormInventoryPoint, String stFormCreateDateFrom,
													String stFormCreateDateTo, String stFormUpdateDateFrom,
													String stFormUpdateDatTo);
	
	// 商品在庫を新規登録
	public void registStock(String stFormStId, String stFormMid,
								 String stFormInventoryDate, String stFormInventoryNum,
								 String stFormMaxStockNum, String stFormOrderPoint,
								 String stFormInventoryPoint);

	// 商品在庫情報更新
	public void editStock(String stFormStId, String stFormMid,
			 					String stFormInventoryDate, String stFormInventoryNum,
			 					String stFormMaxStockNum, String stFormOrderPoint,
			 					String stFormInventoryPoint);
	
	// 商品在庫削除
	public void deleteStock(String stFormStId);
}
