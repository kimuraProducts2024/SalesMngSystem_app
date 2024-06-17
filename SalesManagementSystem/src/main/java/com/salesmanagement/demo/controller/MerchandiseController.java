package com.salesmanagement.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanagement.demo.entity.Merchandise;
import com.salesmanagement.demo.entity.Supplier;
import com.salesmanagement.demo.form.MerListForm;
import com.salesmanagement.demo.form.MerRegiForm;
import com.salesmanagement.demo.service.MerchandiseServiceImpl;
import com.salesmanagement.demo.service.SupplierServiceImpl;

@Controller
@RequestMapping("/merchandise")
public class MerchandiseController {
	
	// DI対象
	@Autowired
	private MerchandiseServiceImpl service;
	
	@Autowired
	private SupplierServiceImpl supService;
	
	// [form-backing-bean]の初期化
	@ModelAttribute
	public MerListForm setForm() {
		
		MerListForm merListForm = new MerListForm();
		
		return merListForm;
	}
	
	// 商品一覧を表示
	@GetMapping("/list")
	public String showMerchandiseList(MerListForm merListForm, ModelAndView mav) {
		
		// 商品一覧情報取得
		merListForm.setMList(service.getAllMerchanSupp());
		
		// ModelAndViewに登録
		mav.addObject(merListForm);
		
		// List.htmlの呼び出し
		return "/merchandise/List";
	}
	
	// 検索ボタン押下
	@PostMapping(value = "/send", params = "search")
	public String serachMerchandiseList(@ModelAttribute MerListForm merListForm, Model model)
	{
		
		// 商品一覧のデータ格納
		merListForm.setMList(		
		service.getSearchMerchanSupp(merListForm.getMFormid(), merListForm.getMFormname(), 
										merListForm.getMFormcreatedatefrom(), merListForm.getMFormcreatedateto(), 
										merListForm.getMFormupdatedatefrom(), merListForm.getMFormupdatedateto()));
		
		model.addAttribute(merListForm);
		
		return "/merchandise/List";
	}
	
	@GetMapping("/Regist")
	public String showMerchandiseRegi() {
		return "/merchandise/Regist";
	}
	
	// 商品登録へボタン押下
	@PostMapping(value = "/send", params = "regist")
	public String sendMerchandiseRegi(MerRegiForm merRegiForm, Model model) {
		
		List<Supplier> supList = supService.getAllSupplier();
		
		model.addAttribute("merRegiForm", merRegiForm);
		model.addAttribute("supList", supList);
		
		// 商品登録画面に遷移
		return "/merchandise/Regist";
	}
	
	@GetMapping("/Confirm")
	public String showMerchandiseConfirm() {
		return "/merchandise/Confirm";
	}
	
	// 新規登録ボタン押下
	@PostMapping(value = "/regist", params = "regist")
	public String registMerchandise(@ModelAttribute MerRegiForm merRegiForm, Model model) {
		
		List<Supplier> supList = supService.getAllSupplier();
		
		for(Supplier supItem : supList) {
			if(supItem.getSupid() == Integer.parseInt(merRegiForm.getMFormSupId())) {
				merRegiForm.setMFormSupName(supItem.getSupname());
			}
		}
		
		merRegiForm.setMFormStatus(1);
		
		model.addAttribute("confirmMsg", "新規登録してよろしいですか？");
		model.addAttribute("merRegiForm", merRegiForm);
		
		return "/merchandise/Confirm";
	}
	
	// 一覧へ戻るボタン押下
	@PostMapping(value="/regist", params="return")
	public String returnMerchandiseList(Model model) {
		
		// 商品一覧フォーム初期化
		MerListForm merListForm = new MerListForm();
		
		// 商品一覧情報取得
		merListForm.setMList(service.getAllMerchanSupp());
				
		// Modelに登録
		model.addAttribute(merListForm);
		
		return "/merchandise/List";
	}
	
	// 登録完了ボタン押下
	@PostMapping(value="/confirm", params="complete")
	public String compRegistMerchandise(@ModelAttribute MerRegiForm merRegiForm, Model model) {
		
		service.registMerchandise(merRegiForm.getMFormId(), merRegiForm.getMFormName(), 
										merRegiForm.getMFormPrice(), merRegiForm.getMFormSupId(),
										merRegiForm.getMFormSupUnit(), merRegiForm.getMFormPuckingNum());
		
		List<Supplier> supList = supService.getAllSupplier();

		model.addAttribute("selectedValue", merRegiForm.getMFormSupId());
		model.addAttribute("supList", supList);
		model.addAttribute("message", "登録が完了しました。");

		return "/merchandise/Regist";
	}
	
	// 登録画面へ戻るボタン押下
	@PostMapping(value="/confirm", params="returnRegi")
	public String returnMerchandiseRegist(@ModelAttribute MerRegiForm merRegiForm, Model model) {
		
		List<Supplier> supList = supService.getAllSupplier();
		
		model.addAttribute("selectedValue", merRegiForm.getMFormSupId());
		model.addAttribute("supList", supList);
		
		return "/merchandise/Regist";
	}
	
	// 一覧画面からの更新ボタン押下
	@GetMapping(value="/edit/{id}", params="edit")
	public String showMerchandiseEdit(MerListForm merListForm, @PathVariable String id, Model model) {
		
		// 商品一覧情報取得
		merListForm.setMList(service.getAllMerchanSupp());
		
		MerRegiForm merRegiForm = new MerRegiForm();
		List<Supplier> supList = supService.getAllSupplier();
		
		for(Merchandise m : merListForm.getMList()) {
			if(m.getMid() == Integer.parseInt(id)) {
				merRegiForm.setMFormId(m.getMid().toString());
				merRegiForm.setMFormName(m.getMname());
				merRegiForm.setMFormPrice(Integer.valueOf(m.getPrice()).toString());
				merRegiForm.setMFormSupId(Integer.valueOf(m.getSupid()).toString());
				merRegiForm.setMFormSupName(m.getSupname());
				merRegiForm.setMFormSupUnit(Integer.valueOf(m.getSupunit()).toString());
				merRegiForm.setMFormPuckingNum(Integer.valueOf(m.getPuckingnum()).toString());
			}
		}
		
		model.addAttribute("merRegiForm", merRegiForm);
		model.addAttribute("selectedValue", merRegiForm.getMFormSupId());
		model.addAttribute("supList", supList);
		
		return "/merchandise/Edit";
	}
	
	// 更新ボタン押下
		@PostMapping(value = "/edit/send", params = "regist")
		public String editMerchandise(@ModelAttribute MerRegiForm merRegiForm, Model model) {
			
			List<Supplier> supList = supService.getAllSupplier();
			
			for(Supplier supItem : supList) {
				if(supItem.getSupid() == Integer.parseInt(merRegiForm.getMFormSupId())) {
					merRegiForm.setMFormSupName(supItem.getSupname());
				}
			}
			
			merRegiForm.setMFormStatus(2);
			
			model.addAttribute("confirmMsg", "更新してよろしいですか？");
			model.addAttribute("merRegiForm", merRegiForm);
			
			return "/merchandise/Confirm";
		}
		
		// 更新画面からの一覧へ戻るボタン押下
		@PostMapping(value="/edit/send", params="returnEdit")
		public String returnEditToMerchandiseList(Model model) {
			
			// 商品一覧フォーム初期化
			MerListForm merListForm = new MerListForm();
			
			// 商品一覧情報取得
			merListForm.setMList(service.getAllMerchanSupp());
					
			// Modelに登録
			model.addAttribute(merListForm);
			
			return "/merchandise/List";
		}
		
		// 更新完了ボタン押下
		@PostMapping(value="/confirm", params="completeEdit")
		public String compEditMerchandise(@ModelAttribute MerRegiForm merRegiForm, Model model) {
			
			service.editMerchandise(merRegiForm.getMFormId(), merRegiForm.getMFormName(), 
											merRegiForm.getMFormPrice(), merRegiForm.getMFormSupId(),
											merRegiForm.getMFormSupUnit(), merRegiForm.getMFormPuckingNum());
			
			List<Supplier> supList = supService.getAllSupplier();

			model.addAttribute("selectedValue", merRegiForm.getMFormSupId());
			model.addAttribute("supList", supList);
			model.addAttribute("message", "更新が完了しました。");

			return "/merchandise/Edit";
		}
		
		// 更新画面へ戻るボタン押下
		@PostMapping(value="/confirm", params="returnEdit")
		public String returnMerchandiseEdit(@ModelAttribute MerRegiForm merRegiForm, Model model) {
			
			List<Supplier> supList = supService.getAllSupplier();
			
			model.addAttribute("selectedValue", merRegiForm.getMFormSupId());
			model.addAttribute("supList", supList);
			
			return "/merchandise/Edit";
		}
		
		// 一覧画面からの削除ボタン押下
		@GetMapping(value="/delete/{id}", params="delete")
		public String showMerchandiseDelete(MerListForm merListForm, @PathVariable String id, Model model) {
			
			// 商品一覧情報取得
			merListForm.setMList(service.getAllMerchanSupp());
			
			MerRegiForm merRegiForm = new MerRegiForm();
			
			for(Merchandise m : merListForm.getMList()) {
				if(m.getMid() == Integer.parseInt(id)) {
					merRegiForm.setMFormId(m.getMid().toString());
					merRegiForm.setMFormName(m.getMname());
					merRegiForm.setMFormPrice(Integer.valueOf(m.getPrice()).toString());
					merRegiForm.setMFormSupId(Integer.valueOf(m.getSupid()).toString());
					merRegiForm.setMFormSupName(m.getSupname());
					merRegiForm.setMFormSupUnit(Integer.valueOf(m.getSupunit()).toString());
					merRegiForm.setMFormPuckingNum(Integer.valueOf(m.getPuckingnum()).toString());
				}
			}
			
			merRegiForm.setMFormStatus(3);
			
			model.addAttribute("confirmMsg", "削除してよろしいですか？");
			model.addAttribute("merRegiForm", merRegiForm);
			model.addAttribute("selectedValue", merRegiForm.getMFormSupId());
			
			return "/merchandise/Confirm";
		}
		
		// 削除完了ボタン押下
		@PostMapping(value = "/confirm", params = "completeDel")
		public String compDeleteMerchandise(@ModelAttribute MerRegiForm merRegiForm, Model model) {

			service.deleteMerchandise(merRegiForm.getMFormId());

			MerListForm merListForm = new MerListForm();
			// 商品一覧情報取得
			merListForm.setMList(service.getAllMerchanSupp());

			model.addAttribute("merListForm", merListForm);
			model.addAttribute("message", "削除が完了しました。");

			return "/merchandise/List";
		}

		// 商品一覧へ戻るボタン押下
		@PostMapping(value = "/confirm", params = "returnDel")
		public String returnDelToMerchandiseList(Model model) {

			MerListForm merListForm = new MerListForm();
			
			// 商品一覧情報取得
			merListForm.setMList(service.getAllMerchanSupp());
			model.addAttribute(merListForm);
			
			// List.htmlの呼び出し
			return "/merchandise/List";
		}
}
