package com.salesmanagement.demo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.salesmanagement.demo.entity.Merchandise;
import com.salesmanagement.demo.entity.Supplier;

@Mapper
public interface MerchanSuppMapper {
	
	// 商品をIDから検索
	public Merchandise findById(int id);
	
	// 商品全データを検索
	public List<Merchandise> findAllMerchandise();
	
	// 商品テーブル、発注先テーブルからデータ取得
	public List<Merchandise> findAllMerchanSupp();
	
	// 検索画面の入力値を条件に、テーブルを検索
	public List<Merchandise> searchAllMerchanSupp(int mFormid, String mFormname, 
													Date mFormcreatedatefrom, Date mFormcreatedateto, 
													Date mFormupdatedatefrom, Date mFormupdatedateto);
	
	// 商品を新規登録
	public void insertMerchandise(int mFormId, String mFormName, int mFormPrice,
							int mFormSupId, int mFormSupUnit, int mFormPuckingNum);
	
	// 商品情報更新
	public void editMerchandise(int mFormId, String mFormName, int mFormPrice,
							int mFormSupId, int mFormSupUnit, int mFormPuckingNum);
	
	// 商品削除
	public void deleteMerchandise(int mFormId);
	
	// 発注先全データを検索
	public List<Supplier> findAllSupplier();
		
	// 検索画面の入力値を条件に、発注先テーブルを検索
	public List<Supplier> searchSupplier(int sFormSupId, String sFormSupName, 
													Date sFormCreateDateFrom, Date sFormCreateDateTo, 
													Date sFormUpdateDateFrom, Date sFormUpdateDateTo);
	
	// 発注先を新規登録
	public void insertSupplier(int sFormSupId, String sFormSupName);
	
	// 発注先情報更新
	public void editSupplier(int sFormSupId, String sFormSupName);
	
	// 商品情報削除
	public void deleteSupplier(int sFormSupId);
}
