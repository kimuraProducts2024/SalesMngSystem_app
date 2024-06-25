package com.salesmanagement.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanagement.demo.entity.Supplier;
import com.salesmanagement.demo.form.LoginForm;
import com.salesmanagement.demo.form.MerListForm;
import com.salesmanagement.demo.form.MerRegiForm;
import com.salesmanagement.demo.form.StockListForm;
import com.salesmanagement.demo.form.StockRegiForm;
import com.salesmanagement.demo.form.SupListForm;
import com.salesmanagement.demo.service.MerchandiseServiceImpl;
import com.salesmanagement.demo.service.StockServiceImpl;
import com.salesmanagement.demo.service.SupplierServiceImpl;

@Controller
@RequestMapping("/main")
public class MainMenuController {
	
	@Autowired
	private MerchandiseServiceImpl merService;
	
	@Autowired
	private SupplierServiceImpl supService;
	
	@Autowired
	private StockServiceImpl stockService;

	// メインメニューを表示
	@GetMapping
	public String showMainMenu() {
		return "common/MainMenu";
	}

	// 発注先一覧を表示
	@PostMapping(value = "/send", params = "gotoSupList")
	public String showSuppList(Model model) {

		SupListForm supListForm = new SupListForm();

		// 発注先一覧情報取得
		supListForm.setSList(supService.getAllSupplier());

		// Modelに登録
		model.addAttribute("supListForm", supListForm);

		return "/supplier/List";
	}

	// 発注先登録画面を表示
	@PostMapping(value = "/send", params = "gotoSupRegi")
	public String showSuppRegi(Model model) {

		SupListForm supListForm = new SupListForm();
		
		// Modelに登録
		model.addAttribute(supListForm);
		
		return "/supplier/Regist";
	}

	// 商品一覧を表示
	@PostMapping(value = "/send", params = "gotoMerList")
	public String showMerList(Model model) {

		MerListForm merListForm = new MerListForm();
		// 商品一覧情報取得
		merListForm.setMList(merService.getAllMerchanSupp());

		// ModelAndViewに登録
		model.addAttribute(merListForm);

		return "/merchandise/List";
	}

	// 商品登録画面を表示
	@PostMapping(value = "/send", params = "gotoMerRegi")
	public String showMerRegi(Model model) {
		
		MerRegiForm merRegiForm = new MerRegiForm();
		List<Supplier> supList = supService.getAllSupplier();

		model.addAttribute("merRegiForm", merRegiForm);
		model.addAttribute("supList", supList);

		// 商品登録画面に遷移
		return "/merchandise/Regist";
	}

	// 商品在庫一覧を表示
	@PostMapping(value = "/send", params = "gotoStockList")
	public String showMerStockList(StockListForm stockListForm, ModelAndView mav) {
		
		// 商品一覧情報取得
		stockListForm.setStList(stockService.getAllStockMerchan());
		
		// ModelAndViewに登録
		mav.addObject(stockListForm);
		
		// List.htmlの呼び出し
		return "/stock/List";
	}

	// 商品在庫登録画面を表示
	@PostMapping(value = "/send", params = "gotoStockRegi")
	public String showMerStockRegi(StockRegiForm stockRegiForm, Model model) {
		
		model.addAttribute("stockRegiForm", stockRegiForm);
		
		// 商品登録画面に遷移
		return "/stock/Regist";
	}
	
	// ログイン画面を表示
	@PostMapping(value = "/send", params = "logOut")
	public String showLogin(LoginForm loginForm, Model model) {
		
		model.addAttribute("loginForm", loginForm);
		
		return "/login/Login";
	}
		
}
