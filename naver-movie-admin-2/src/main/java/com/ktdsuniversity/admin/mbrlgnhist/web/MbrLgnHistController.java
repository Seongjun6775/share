package com.ktdsuniversity.admin.mbrlgnhist.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.admin.mbr.vo.MbrVO;
import com.ktdsuniversity.admin.mbrlgnhist.service.MbrLgnHistService;
import com.ktdsuniversity.admin.mbrlgnhist.vo.MbrLgnHistVO;

@Controller
public class MbrLgnHistController {
	
	@Autowired
	private MbrLgnHistService mbrLgnHistService;
	
	/*
	 * @GetMapping("/mbr/logout") public String doLogoutAdminMember(HttpSession
	 * session) { MbrVO mbr = (MbrVO)session.getAttribute("__ADMIN__");
	 * session.invalidate(); MbrLgnHistVO mbrLgnHistVO = new MbrLgnHistVO();
	 * mbrLgnHistVO.setAct("logout"); mbrLgnHistVO.setMbrId(mbr.getMbrId());
	 * mbrLgnHistVO.setIp(mbr.getLstLgnIp());
	 * mbrLgnHistService.createMbrLgnHist(mbrLgnHistVO); return "redirect:/"; }
	 */
	
}
