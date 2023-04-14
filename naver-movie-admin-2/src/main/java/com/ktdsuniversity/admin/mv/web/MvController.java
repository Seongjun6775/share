package com.ktdsuniversity.admin.mv.web;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ktdsuniversity.admin.common.util.DownloadUtil;
import com.ktdsuniversity.admin.mv.service.MvService;
import com.ktdsuniversity.admin.mv.vo.MvVO;

@Controller
public class MvController {
	
	@Autowired
	private MvService mvService;
	
	@Value("${upload.mv.poster.path:/naver-movie-admin/files/mv/poster}")
	private String profilePath;
	
	@GetMapping("/mv/list")
	public String viewMvListPage(MvVO mvVO) {
		return "mv/list";
	}
	@GetMapping("/mv/create")
	public String viewMvcreatePage(MvVO mvVO) {
		return "mv/create";
	}
	@GetMapping("/mv/detail/{mvId}")
	public String viewMvDetailPage(@PathVariable String mvId, Model model) {
		MvVO mvVO = mvService.readOneMvById(mvId);
		model.addAttribute("mvVO", mvVO);
		return "mv/detail";
	}
	@GetMapping("/mv/pstr/{fileName}")
	public void downloadPstr(@PathVariable String filename,
							 HttpServletRequest req,
							 HttpServletResponse res) {
		File imageFile = new File(profilePath, filename);
		if(imageFile.exists() && imageFile.isFile()) {
			DownloadUtil dnUtil = new DownloadUtil(res, req, profilePath + "/" + filename);
			dnUtil.download(filename);
		}else {
			DownloadUtil dnUtil = new DownloadUtil(res, req, profilePath + "/baseProfile.png");
			dnUtil.download("baseProfile.png");
		}
	}
	
	
}
