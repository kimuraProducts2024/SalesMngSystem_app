package com.salesmanagement.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesmanagement.demo.entity.LoginUser;
import com.salesmanagement.demo.form.LoginForm;
import com.salesmanagement.demo.service.LoginServiceImpl;

@Controller
public class LoginController {

	// DI対象
	@Autowired
	LoginServiceImpl loginService;
	
	// [form-backing-bean]の初期化
	@ModelAttribute
	LoginForm setupForm() {
		LoginForm loginForm = new LoginForm();
		loginForm.setLFormErrcount("0");
		loginForm.setLFormErrMsg("");
		return loginForm;
	}
	
	// ログイン画面を表示
	@GetMapping("/")
	public String showLogin(LoginForm loginForm, ModelAndView mav) {
		mav.addObject(loginForm);
		
		return "/login/Login";
	}
	
	@PostMapping("/login")
	public String tryLogin(LoginForm loginForm, RedirectAttributes redirectAttributes) {
		
		LoginUser loginUser = new LoginUser();
		
		// ユーザ情報取得
		loginUser = loginService.selectLoginUser(loginForm.getLFormUserid());
		
		// ユーザIDが存在しない場合
		if(loginUser == null) {
			loginForm.setLFormErrMsg("ユーザIDは存在しません。");
			redirectAttributes.addFlashAttribute("loginForm", loginForm);
			
			return "redirect:/";
		}
		
		// 試行回数が10回以上の場合
		if(loginUser.getErrCount() >= 10) {
			loginForm.setLFormErrMsg("\\r\\n指定回数を超えています。\\r\\n管理者にお問い合わせして下さい。");
			redirectAttributes.addFlashAttribute("loginForm", loginForm);
			
			return "redirect:/";
		}
		
		// パスワードが異なる場合
		if(!loginForm.getLFormPassword().equals(loginUser.getPassword())) {
			
			if(loginUser.getErrCount() + 1 >= 10) {
				loginForm.setLFormErrMsg("パスワードが異なります。\\r\\n指定回数を超えました。\\r\\n管理者にお問い合わせして下さい。");
			} else {
				loginForm.setLFormErrMsg("パスワードが異なります。");
			}
			
			// エラーカウントの更新
			loginService.updateErrCount(loginUser.getUserId(), loginUser.getErrCount());
			
			loginForm.setLFormErrcount(Integer.valueOf(loginUser.getErrCount() + 1).toString());			
			redirectAttributes.addFlashAttribute("loginForm", loginForm);
			
			return "redirect:/";
		}
		// ユーザID、パスワードが一致した場合
		else {
			return "/common/MainMenu";
		}
	}
	
	// パスワード変更画面を表示
	@GetMapping("/login/UpdateInfo")
	public String showUpdateInfo(LoginForm loginForm, Model model) {
		model.addAttribute(loginForm);
		
		return "/login/UpdateInfo";
	}
	
	// ログイン画面に戻るボタン押下
	@PostMapping("/login/return")
	public String returnLogin() {
		
		return "/login/Login";
	}
	
	// パスワード変更ボタン押下
	@PostMapping(value = "/login/UpdateInfo", params = "complete")
	public String updateUserInfo(LoginForm loginForm, RedirectAttributes redirectAttributes) {
		
		// パスワード、パスワード（確認用）の値が異なる場合
		if(!loginForm.getLFormPassword().equals(loginForm.getLFormConfpassword())) {
			loginForm.setLFormErrMsg("パスワード、パスワード（確認用）が異なります。");
			redirectAttributes.addFlashAttribute("loginForm", loginForm);
			
			return "redirect:/login/UpdateInfo";
		}
		
		// LoginUserテーブルの更新
		loginService.updatePassword(loginForm.getLFormUserid(), loginForm.getLFormPassword());
		
		loginForm.setLFormMessage("パスワード変更しました。");
		redirectAttributes.addFlashAttribute("loginForm", loginForm);
		
		return "redirect:/login/UpdateInfo";
	}
	
	// 新規登録画面を表示
	@GetMapping("/login/RegistUser")
	public String showRegistUser(LoginForm loginForm, Model model) {
		model.addAttribute(loginForm);
		
		return "/login/RegistUser";
	}
	
	// ユーザ登録ボタン押下
	@PostMapping(value = "/login/regist", params = "complete")
	public String registLoginUser(LoginForm loginForm, RedirectAttributes redirectAttributes) {
		
		// パスワード、パスワード（確認用）の値が異なる場合
		if(!loginForm.getLFormPassword().equals(loginForm.getLFormConfpassword())) {
			loginForm.setLFormErrMsg("パスワード、パスワード（確認用）が異なります。");
			redirectAttributes.addFlashAttribute("loginForm", loginForm);
			
			return "redirect:/login/RegistUser";
		}
		
		// ユーザ登録
		loginService.inserLoginUser(loginForm.getLFormUserid(), loginForm.getLFormPassword());
		
		loginForm.setLFormMessage("ユーザ登録が完了しました。");
		redirectAttributes.addFlashAttribute("loginForm", loginForm);
		
		return "redirect:/login/RegistUser";
	}
	
}
