package com.salesmanagement.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanagement.demo.entity.Stock;
import com.salesmanagement.demo.mapper.StockMapper;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockMapper mapper;
	
	@Override
	// 商品在庫をIDから検索
	public Stock findById(String stFormStId) {
		return mapper.findById(Integer.parseInt(stFormStId));
	}
	
	// 商品在庫テーブル、商品テーブルからデータ取得
	@Override
	public List<Stock> getAllStockMerchan() {
		return mapper.findAllStockMerchan();
	}

	// 検索画面の入力値を条件に、テーブルを検索
	@Override
	public List<Stock> getSearchStockMerchan(String stFormStId, String stFormMid, 
												String stFormInventoryDateFrom, String stFormInventoryDateTo, 
												String stFormInventoryPoint, String stFormCreateDateFrom,
												String stFormCreateDateTo, String stFormUpdateDateFrom,
												String stFormUpdateDatTo) {
		try {
				int stId = stFormStId.equals("") ? 0 : Integer.parseInt(stFormStId);
				int stMid = stFormMid.equals("") ? 0 : Integer.parseInt(stFormMid);
				Date inventoryDateFrom = stFormInventoryDateFrom.equals("") ? null :
						new SimpleDateFormat("yyyy/MM/dd").parse(stFormInventoryDateFrom.replaceAll("-", "/"));
			
				Date inventoryDateTo = stFormInventoryDateTo.equals("") ? null :
						new SimpleDateFormat("yyyy/MM/dd").parse(stFormInventoryDateTo.replace("-", "/"));
				Date createdatefrom = stFormCreateDateFrom.equals("") ? null :
						new SimpleDateFormat("yyyy/MM/dd").parse(stFormCreateDateFrom.replaceAll("-", "/"));
				Date createdateto = stFormCreateDateTo.equals("") ? null : 
						new SimpleDateFormat("yyyy/MM/dd").parse(stFormCreateDateTo.replaceAll("-", "/"));
				Date updatedatefrom = stFormUpdateDateFrom.equals("") ? null : 
						new SimpleDateFormat("yyyy/MM/dd").parse(stFormUpdateDateFrom.replaceAll("-", "/"));
				Date updatedateto = stFormUpdateDatTo.equals("") ? null : 
						new SimpleDateFormat("yyyy/MM/dd").parse(stFormUpdateDatTo.replaceAll("-", "/"));
				
				
			return mapper.searchAllStockMerchan(stId, stMid, inventoryDateFrom, inventoryDateTo, 
											stFormInventoryPoint, createdatefrom, createdateto, 
											updatedatefrom, updatedateto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 商品在庫を新規登録
	@Override
	public void registStock(String stFormStId, String stFormMid,
			 					String stFormInventoryDate, String stFormInventoryNum,
			 					String stFormMaxStockNum, String stFormOrderPoint,
			 					String stFormInventoryPoint) {
		try {
			mapper.insertStock(Integer.parseInt(stFormStId), Integer.parseInt(stFormMid), 
									new SimpleDateFormat("yyyy/MM/dd").parse(stFormInventoryDate.replace("-", "/")),
									Integer.parseInt(stFormInventoryNum), Integer.parseInt(stFormMaxStockNum), 
									Integer.parseInt(stFormOrderPoint), stFormInventoryPoint);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// 商品在庫情報更新
	@Override
	public void editStock(String stFormStId, String stFormMid,
			 					String stFormInventoryDate, String stFormInventoryNum,
			 					String stFormMaxStockNum, String stFormOrderPoint,
			 					String stFormInventoryPoint) {
		try {
			mapper.editStock(Integer.parseInt(stFormStId), Integer.parseInt(stFormMid), 
									new SimpleDateFormat("yyyy/MM/dd").parse(stFormInventoryDate.replace("-", "/")),
									Integer.parseInt(stFormInventoryNum), Integer.parseInt(stFormMaxStockNum), 
									Integer.parseInt(stFormOrderPoint), stFormInventoryPoint);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// 商品在庫削除
	@Override
	public void deleteStock(String stFormStId) {
		mapper.deleteStock(Integer.parseInt(stFormStId));
	}
}
