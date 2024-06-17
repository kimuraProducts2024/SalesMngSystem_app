package com.salesmanagement.demo.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanagement.demo.entity.Stock;
import com.salesmanagement.demo.form.StockListForm;
import com.salesmanagement.demo.form.StockRegiForm;
import com.salesmanagement.demo.service.StockServiceImpl;

@Controller
@RequestMapping("/stock")
public class StockController {
	
	// DI対象
	@Autowired
	private StockServiceImpl stockService;
	
	// [form-backing-bean]の初期化
	@ModelAttribute
	public StockListForm setForm() {
		
		StockListForm stockListForm = new StockListForm();
		
		return stockListForm;
	}
	
	// 商品在庫一覧を表示
	@GetMapping("/list")
	public String showStockList(StockListForm stockListForm, ModelAndView mav) {
		
		// 商品一覧情報取得
		stockListForm.setStList(stockService.getAllStockMerchan());
		
		// ModelAndViewに登録
		mav.addObject(stockListForm);
		
		// List.htmlの呼び出し
		return "/stock/List";
	}
	
	// 検索ボタン押下
	@PostMapping(value = "/send", params = "search")
	public String serachStockList(@ModelAttribute StockListForm stockListForm, Model model)
	{
		
		// 商品一覧のデータ格納
		stockListForm.setStList(		
		stockService.getSearchStockMerchan(stockListForm.getStFormStId(), stockListForm.getStFormMid(), 
								stockListForm.getStFormInventoryDateFrom(), stockListForm.getStFormInventoryDateTo(),
								stockListForm.getStFormInventoryPoint(), stockListForm.getStFormCreateDateFrom(),
								stockListForm.getStFormCreateDateTo(), stockListForm.getStFormUpdateDateFrom(),
								stockListForm.getStFormUpdateDatTo()));
		
		model.addAttribute(stockListForm);
		
		return "/stock/List";
	}
	
	@GetMapping("/Regist")
	public String showStockRegi() {
		return "/stock/Regist";
	}
	
	// 商品在庫登録へボタン押下
	@PostMapping(value = "/send", params = "regist")
	public String sendStockRegi(StockRegiForm stockRegiForm, Model model) {
		
		model.addAttribute("stockRegiForm", stockRegiForm);
		
		// 商品登録画面に遷移
		return "/stock/Regist";
	}
	
	@GetMapping("/Confirm")
	public String showStockConfirm() {
		return "/stock/Confirm";
	}
	
	// 新規登録ボタン押下
	@PostMapping(value = "/regist", params = "regist")
	public String registStock(@ModelAttribute StockRegiForm stockRegiForm, Model model) {
		
		stockRegiForm.setStFormStatus(1);
		
		model.addAttribute("confirmMsg", "新規登録してよろしいですか？");
		model.addAttribute("stockRegiForm", stockRegiForm);
		
		return "/stock/Confirm";
	}
	
	// 一覧へ戻るボタン押下
	@PostMapping(value="/regist", params="return")
	public String returnStockList(Model model) {
		
		// 商品在庫一覧フォーム初期化
		StockListForm stockListForm = new StockListForm();
		
		// 商品在庫一覧情報取得
		stockListForm.setStList(stockService.getAllStockMerchan());
				
		// Modelに登録
		model.addAttribute(stockListForm);
		
		return "/stock/List";
	}
	
	// 登録完了ボタン押下
	@PostMapping(value="/confirm", params="complete")
	public String compRegistStock(@ModelAttribute StockRegiForm stockRegiForm, Model model) {

		stockService.registStock(stockRegiForm.getStFormStId(), stockRegiForm.getStFormMid(), 
									stockRegiForm.getStFormInventoryDate(), stockRegiForm.getStFormInventoryNum(), 
									stockRegiForm.getStFormMaxStockNum(), stockRegiForm.getStFormOrderPoint(), 
									stockRegiForm.getStFormInventoryPoint());
		
		model.addAttribute("message", "登録が完了しました。");
		model.addAttribute("stockRegiForm", stockRegiForm);

		return "/stock/Regist";
	}
	
	// 登録画面へ戻るボタン押下
	@PostMapping(value="/confirm", params="returnRegi")
	public String returnStockRegist(@ModelAttribute StockRegiForm stockRegiForm, Model model) {
		
		model.addAttribute("stockRegiForm", stockRegiForm);
		
		return "/stock/Regist";
	}
	
	// 一覧画面からの更新ボタン押下
	@GetMapping(value="/edit/{id}/{mid}", params="edit")
	public String showMerchandiseEdit(@PathVariable String id, @PathVariable String mid, Model model) {
		
		StockRegiForm stockRegiForm = new StockRegiForm();
		
		Stock stock = stockService.findById(id);
		
		stockRegiForm.setStFormStId(id);
		stockRegiForm.setStFormMid(mid);
		stockRegiForm.setStFormInventoryDate(new SimpleDateFormat("yyyy/MM/dd").format(stock.getInventoryDate()));
		stockRegiForm.setStFormInventoryNum(Integer.valueOf(stock.getInventoryNum()).toString());
		stockRegiForm.setStFormMaxStockNum(Integer.valueOf(stock.getMaxStockNum()).toString());
		stockRegiForm.setStFormOrderPoint(Integer.valueOf(stock.getOrderPoint()).toString());
		stockRegiForm.setStFormInventoryPoint(stock.getInventoryPoint());
		
		model.addAttribute("stockRegiForm", stockRegiForm);
		
		return "/stock/Edit";
	}
	
	// 更新ボタン押下
		@PostMapping(value = "/edit/send", params = "regist")
		public String editStock(@ModelAttribute StockRegiForm stockRegiForm, Model model) {
			
			stockRegiForm.setStFormStatus(2);
			
			model.addAttribute("confirmMsg", "更新してよろしいですか？");
			model.addAttribute("stockRegiForm", stockRegiForm);
			
			return "/stock/Confirm";
		}
		
		// 更新画面からの一覧へ戻るボタン押下
		@PostMapping(value="/edit/send", params="returnEdit")
		public String returnEditToStockList(Model model) {
			
			// 商品在庫一覧フォーム初期化
			StockListForm stockListForm = new StockListForm();
			
			// 商品在庫一覧情報取得
			stockListForm.setStList(stockService.getAllStockMerchan());
					
			// Modelに登録
			model.addAttribute(stockListForm);
			
			return "/stock/List";
		}
		
		// 更新完了ボタン押下
		@PostMapping(value="/confirm", params="completeEdit")
		public String compEditStock(@ModelAttribute StockRegiForm stockRegiForm, Model model) {
			
			stockService.editStock(stockRegiForm.getStFormStId(), stockRegiForm.getStFormMid(), 
										stockRegiForm.getStFormInventoryDate(), stockRegiForm.getStFormInventoryNum(), 
										stockRegiForm.getStFormMaxStockNum(), stockRegiForm.getStFormOrderPoint(), 
										stockRegiForm.getStFormInventoryPoint());
			
			model.addAttribute("message", "更新が完了しました。");
			model.addAttribute("stockRegiForm", stockRegiForm);
			
			return "/stock/Edit";
		}
		
		// 更新画面へ戻るボタン押下
		@PostMapping(value="/confirm", params="returnEdit")
		public String returnStockEdit(@ModelAttribute StockRegiForm stockRegiForm, Model model) {
			
			model.addAttribute("stockRegiForm", stockRegiForm);
			
			return "/stock/Edit";
		}
		
		// 一覧画面からの削除ボタン押下
		@GetMapping(value="/delete/{id}/{mid}", params="delete")
		public String showMerchandiseDelete(@PathVariable String id, @PathVariable String mid, Model model) {
			
			StockRegiForm stockRegiForm = new StockRegiForm();
			
			Stock stock = stockService.findById(id);
			
			stockRegiForm.setStFormStId(id);
			stockRegiForm.setStFormMid(mid);
			stockRegiForm.setStFormInventoryDate(new SimpleDateFormat("yyyy/MM/dd").format(stock.getInventoryDate()));
			stockRegiForm.setStFormInventoryNum(Integer.valueOf(stock.getInventoryNum()).toString());
			stockRegiForm.setStFormMaxStockNum(Integer.valueOf(stock.getMaxStockNum()).toString());
			stockRegiForm.setStFormOrderPoint(Integer.valueOf(stock.getOrderPoint()).toString());
			stockRegiForm.setStFormInventoryPoint(stock.getInventoryPoint());
			
			model.addAttribute("stockRegiForm", stockRegiForm);
			
			stockRegiForm.setStFormStatus(3);
			
			model.addAttribute("confirmMsg", "削除してよろしいですか？");
			model.addAttribute("stockRegiForm", stockRegiForm);
			
			return "/stock/Confirm";
		}
		
		// 削除完了ボタン押下
		@PostMapping(value = "/confirm", params = "completeDel")
		public String compDeleteStock(@ModelAttribute StockRegiForm stockRegiForm, Model model) {

			stockService.deleteStock(stockRegiForm.getStFormStId());

			model.addAttribute("stockRegiForm", stockRegiForm);
			model.addAttribute("message", "削除が完了しました。");

			return "/stock/List";
		}

		// 商品一覧へ戻るボタン押下
		@PostMapping(value = "/confirm", params = "returnDel")
		public String returnDelToStockList(Model model) {

			// 商品在庫一覧フォーム初期化
			StockListForm stockListForm = new StockListForm();
			
			// 商品在庫一覧情報取得
			stockListForm.setStList(stockService.getAllStockMerchan());
					
			// Modelに登録
			model.addAttribute(stockListForm);
			
			// List.htmlの呼び出し
			return "/stock/List";
		}
}
