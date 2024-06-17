package com.salesmanagement.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanagement.demo.entity.Supplier;
import com.salesmanagement.demo.form.SupListForm;
import com.salesmanagement.demo.service.SupplierServiceImpl;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

	// DI対象
	@Autowired
	private SupplierServiceImpl service;
	
	// [form-backing-bean]の初期化
	@ModelAttribute
	public SupListForm setForm() {
		return new SupListForm();
	}
	
	// 発注先一覧を表示
	@GetMapping("/list")
	public String showSuppList(SupListForm supListForm, Model model) {

		// 発注先一覧情報取得
		supListForm.setSList(service.getAllSupplier());

		// Modelに登録
		model.addAttribute("supListForm", supListForm);

		// List.htmlの呼び出し
		return "/supplier/List";
	}
	
	// 検索ボタン押下
	@PostMapping(value = "/send", params = "search")
	public String searchSupplierList(SupListForm supListForm, Model model) {
		
		// 発注先一覧のデータ格納
		supListForm.setSList(
		service.searchSupplier(supListForm.getSFormSupId(), supListForm.getSFormSupName(), 
				supListForm.getSFormCreateDateFrom(), supListForm.getSFormCreateDateTo(), 
				supListForm.getSFormUpdateDateFrom(), supListForm.getSFormUpdateDateTo()));
		
		model.addAttribute(supListForm);
		
		return "supplier/List";
	}

	// 発注先登録へボタン押下
	@PostMapping(value = "/send", params = "regist")
	public String showSuppRegi() {
		
		// 発注先登録画面に遷移
		return "/supplier/Regist";
	}
	
	// 新規登録ボタン押下
	@PostMapping(value = "/regist", params = "regist")
	public String registSupplier(SupListForm supListForm, Model model) {
		
		supListForm.setSFormStatus(1);
		
		model.addAttribute("confirmMsg", "新規登録してよろしいですか？");
		model.addAttribute(supListForm);
		
		return "/supplier/Confirm";
	}
	
	
	// 一覧へ戻るボタン押下
	@PostMapping(value = "/regist", params = "return")
	public String returnSupplierList(Model model) {

		// 発注先一覧フォーム初期化
		SupListForm supListForm = new SupListForm();

		// 発注先一覧情報取得
		supListForm.setSList(service.getAllSupplier());

		// Modelに登録
		model.addAttribute("supListForm", supListForm);

		// List.htmlの呼び出し
		return "/supplier/List";
	}
	
	// 登録完了ボタン押下
	@PostMapping(value = "/confirm", params = "complete")
	public String compRegistSupplier(SupListForm supListForm, Model model) {
		
		service.insertSupplier(supListForm.getSFormSupId(), supListForm.getSFormSupName());
		
		model.addAttribute(supListForm);
		model.addAttribute("message", "登録が完了しました。");
		
		return "/supplier/Regist";
	}
	
	// 登録画面へ戻るボタン押下
	@PostMapping(value = "/confirm", params = "returnRegi")
	public String returnSupplierRegist(SupListForm supListForm, Model model) {
		
		return "/supplier/Regist";
	}
	
	// 一覧画面からの更新ボタン押下
	@GetMapping(value = "/edit/{id}", params = "edit")
	public String showSupplierEdit(SupListForm supListForm, @PathVariable String id, Model model) {
		
		// 発注先一覧情報取得
		supListForm.setSList(service.getAllSupplier());
		
		for(Supplier s : supListForm.getSList()) {
			if(s.getSupid() == Integer.parseInt(id)) {
				supListForm.setSFormSupId(Integer.valueOf(s.getSupid()).toString());
				supListForm.setSFormSupName(s.getSupname());
			}
		}
		
		model.addAttribute(supListForm);
		
		return "/supplier/Edit";
	}
	
	// 更新ボタン押下
	@PostMapping(value = "/edit/send", params = "edit")
	public String editSupplier(@ModelAttribute SupListForm supListForm, Model model) {
		
		supListForm.setSFormStatus(2);
		
		model.addAttribute("confirmMsg", "更新してよろしいですか？");
		model.addAttribute("supListForm", supListForm);
		
		return "/supplier/Confirm";
	}
	
	// 更新画面からの一覧へ戻るボタン押下
	@PostMapping(value = "/edit/send", params = "returnEdit")
	public String returnEditToSupplierList(Model model) {
		
		// 発注先一覧フォーム初期化
		SupListForm supListForm = new SupListForm();
		
		// 発注先一覧情報取得
		supListForm.setSList(service.getAllSupplier());
		
		// Modelに登録
		model.addAttribute(supListForm);
		
		return "/supplier/List";
	}
	
	// 更新完了ボタン押下
	@PostMapping(value = "/confirm", params = "completeEdit")
	public String compEditSupplier(@ModelAttribute SupListForm supListForm, Model model) {
		
		service.editSupplier(supListForm.getSFormSupId(), supListForm.getSFormSupName());
		
		model.addAttribute("message", "更新が完了しました。");
		
		return "/supplier/Edit";
	}
	
	// 一覧画面からの削除ボタン押下
	@GetMapping(value = "/delete/{id}", params = "delete")
	public String showSupplierDelete(SupListForm supListForm, @PathVariable String id, Model model) {
		
		// 発注先一覧情報取得
		supListForm.setSList(service.getAllSupplier());
		
		for(Supplier s : supListForm.getSList()) {
			if(s.getSupid() == Integer.parseInt(id)) {
				supListForm.setSFormSupId(Integer.valueOf(s.getSupid()).toString());
				supListForm.setSFormSupName(s.getSupname());
			}
		}
		
		supListForm.setSFormStatus(3);
		
		model.addAttribute("confirmMsg", "削除してよろしいですか？");
		model.addAttribute("supListForm", supListForm);
		
		return "/supplier/Confirm";
	}
	
	// 削除完了ボタン押下
	@PostMapping(value = "/confirm", params = "completeDel")
	public String compDeleteSupplier(SupListForm supListForm, Model model) {
		
		service.deleteSupplier(supListForm.getSFormSupId());
		
		// 発注先一覧情報取得
		supListForm.setSList(service.getAllSupplier());
		
		model.addAttribute("message", "削除が完了しました。");
		model.addAttribute(supListForm);
		
		return "/supplier/List";
	}
	
	
	// 発注先一覧へ戻るボタン押下
	@PostMapping(value = "/confirm", params = "returnDel")
	public String returnDelToSupplierList(SupListForm supListForm, Model model) {
		
		// 発注先一覧情報取得
		supListForm.setSList(service.getAllSupplier());
		model.addAttribute(supListForm);
		
		return "/supplier/List";
	}
}
