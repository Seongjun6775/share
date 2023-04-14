package com.ktdsuniversity.admin.mbr.web;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.admin.mbr.service.MbrService;
import com.ktdsuniversity.admin.mbr.vo.MbrVO;
import com.ktdsuniversity.admin.mbrlgnhist.vo.MbrLgnHistVO;

@Controller
public class MbrController {

	private Logger logger = LoggerFactory.getLogger(MbrController.class);

	@Autowired
	private MbrService mbrService;

	@GetMapping("/")
	public String viewAdminLoginPage() {
		return "mbr/lgn";
	}

	@GetMapping("/mbr/list")
	public String viewMbrListPage(Model model) {
		List<MbrVO> mbrList = mbrService.readAllAdminMbr();
		model.addAttribute("mbrList", mbrList);
		return "mbr/list";
	}

	@GetMapping("/mbr/logout")
	public String doLogoutAdminMember(@SessionAttribute("__ADMIN__") MbrVO mbrVO, HttpServletRequest request, HttpSession session) {
		session.invalidate();
		MbrLgnHistVO mbrLgnHistVO = new MbrLgnHistVO();
		mbrLgnHistVO.setAct("logout");
		mbrLgnHistVO.setMbrId(mbrVO.getMbrId());
		mbrLgnHistVO.setIp(mbrVO.getLstLgnIp());
		mbrService.logoutAdminMbr(mbrLgnHistVO);
		return "redirect:/";
	}

}
