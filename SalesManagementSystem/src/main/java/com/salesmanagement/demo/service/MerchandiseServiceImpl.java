package com.salesmanagement.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanagement.demo.entity.Merchandise;
import com.salesmanagement.demo.mapper.MerchanSuppMapper;

@Service
public class MerchandiseServiceImpl implements MerchandiseService {

	@Autowired
	private MerchanSuppMapper mapper;
	
	// 商品検索
	@Override
	public Merchandise getMerchandise(int mid) {
		return mapper.findById(mid);
	}
	
	// 全商品検索
	@Override
	public List<Merchandise> getAllMerchandise() {
		return mapper.findAllMerchandise();
	}

	// 商品テーブル、発注先テーブルからデータ取得
	@Override
	public List<Merchandise> getAllMerchanSupp() {
		return mapper.findAllMerchanSupp();
	}
	
	@Override
	// 検索画面の入力値を条件に、テーブルを検索
	public List<Merchandise> getSearchMerchanSupp(String mFormid, String mFormname, 
													String mFormcreatedatefrom, String mFormcreatedateto, 
													String mFormupdatedatefrom, String mFormupdatedateto) {	
		try {
			int mid = mFormid.equals("") ? Integer.parseInt("0")  : Integer.parseInt(mFormid);
			Date createdatefrom = mFormcreatedatefrom.equals("") ? null :
						new SimpleDateFormat("yyyy/MM/dd").parse(mFormcreatedatefrom.replaceAll("-", "/"));
			Date createdateto = mFormcreatedateto.equals("") ? null : 
						new SimpleDateFormat("yyyy/MM/dd").parse(mFormcreatedateto.replaceAll("-", "/"));
			Date updatedatefrom = mFormupdatedatefrom.equals("") ? null : 
						new SimpleDateFormat("yyyy/MM/dd").parse(mFormupdatedatefrom.replaceAll("-", "/"));
			Date updatedateto = mFormupdatedateto.equals("") ? null : 
						new SimpleDateFormat("yyyy/MM/dd").parse(mFormupdatedateto.replaceAll("-", "/"));
			
			return mapper.searchAllMerchanSupp(mid, mFormname, createdatefrom, 
										createdateto, updatedatefrom, updatedateto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 商品を新規登録
	@Override
	public void registMerchandise(String mFormId, String mFormName, String mFormPrice,
									String mFormSupId, String mFormSupUnit, String mFormPuckingNum) {
		mapper.insertMerchandise(Integer.parseInt(mFormId) , mFormName, Integer.parseInt(mFormPrice), 
				Integer.parseInt(mFormSupId), Integer.parseInt(mFormSupUnit), Integer.parseInt(mFormPuckingNum));
	}
	
	// 商品情報更新
	@Override
	public void editMerchandise(String mFormId, String mFormName, String mFormPrice,
			String mFormSupId, String mFormSupUnit, String mFormPuckingNum) {
		mapper.editMerchandise(Integer.parseInt(mFormId) , mFormName, Integer.parseInt(mFormPrice), 
				Integer.parseInt(mFormSupId), Integer.parseInt(mFormSupUnit), Integer.parseInt(mFormPuckingNum));
	}
	
	// 商品削除
	@Override
	public void deleteMerchandise(String mFormId) {
		mapper.deleteMerchandise(Integer.parseInt(mFormId));
	}
}
