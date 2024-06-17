package com.salesmanagement.demo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.salesmanagement.demo.entity.Stock;

@Mapper
public interface StockMapper {
	
	// 商品在庫をIDから検索
	public Stock findById(int id);
	
	// 商品在庫テーブル、商品テーブルからデータ取得
	public List<Stock> findAllStockMerchan();
	
	// 検索画面の入力値を条件に、テーブルを検索
	public List<Stock> searchAllStockMerchan(int stFormStId,int stFormMid, Date stFormInventoryDateFrom, 
													Date stFormInventoryDateTo, String stFormInventoryPoint, 
													Date stFormCreateDateFrom, Date stFormCreateDateTo,
													Date stFormUpdateDateFrom, Date stFormUpdateDatTo);
	
	// 商品在庫を新規登録
	public void insertStock(int stFormStId, int stFormMid,
								Date stFormInventoryDate, int stFormInventoryNum,
								int stFormMaxStockNum, int stFormOrderPoint,
								String stFormInventoryPoint);
	
	// 商品在庫情報更新
	public void editStock(int stFormStId, int stFormMid,
								Date stFormInventoryDate, int stFormInventoryNum,
								int stFormMaxStockNum, int stFormOrderPoint,
								String stFormInventoryPoint);
	
	// 商品在庫削除
	public void deleteStock(int stFormStId);
}
