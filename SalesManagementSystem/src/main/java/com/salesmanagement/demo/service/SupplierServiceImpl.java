package com.salesmanagement.demo.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanagement.demo.entity.Supplier;
import com.salesmanagement.demo.mapper.MerchanSuppMapper;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private MerchanSuppMapper mapper;
	
	// 発注先全データを検索
	@Override
	public List<Supplier> getAllSupplier() {
		return mapper.findAllSupplier();
	}
	
	// 検索画面の入力値を条件に、テーブルを検索
	@Override
	public List<Supplier> searchSupplier(String sFormSupId, String sFormSupName, 
													String sFormCreateDateFrom, String sFormCreateDateTo, 
													String sFormUpdateDateFrom, String sFormUpdateDateTo) {
		try {
			return mapper.searchSupplier(sFormSupId.equals("") ? Integer.parseInt("0") : Integer.parseInt(sFormSupId), 
					sFormSupName, 
					sFormCreateDateFrom.equals("") ? null :
					new SimpleDateFormat("yyyy/MM/dd").parse(sFormCreateDateFrom.replace("-","/")), 
					sFormCreateDateTo.equals("") ? null :
					new SimpleDateFormat("yyyy/MM/dd").parse(sFormCreateDateTo.replace("-", "/")),
					sFormUpdateDateFrom.equals("") ? null :
					new SimpleDateFormat("yyyy/MM/dd").parse(sFormUpdateDateFrom.replace("-","/")), 
					sFormUpdateDateTo.equals("") ? null :
					new SimpleDateFormat("yyyy/MM/dd").parse(sFormUpdateDateTo.replaceAll("-", "/")));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 発注先を新規登録
	@Override
	public void insertSupplier(String sFormSupId, String sFormSupName) {
		mapper.insertSupplier(Integer.parseInt(sFormSupId), sFormSupName);
	}
	
	// 商品情報更新
	@Override
	public void editSupplier(String sFormSupId, String sFormSupName) {
		mapper.editSupplier(Integer.parseInt(sFormSupId), sFormSupName);
	}
	
	// 商品情報削除
	@Override
	public void deleteSupplier(String sFormSupId) {
		mapper.deleteSupplier(Integer.parseInt(sFormSupId));
	}
}
