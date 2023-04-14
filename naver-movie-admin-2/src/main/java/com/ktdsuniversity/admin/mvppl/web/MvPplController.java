package com.ktdsuniversity.admin.mvppl.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.admin.common.util.DownloadUtil;
import com.ktdsuniversity.admin.mvppl.service.MvPplService;
import com.ktdsuniversity.admin.mvppl.vo.MvPplVO;

@Controller
public class MvPplController {
	
	private static final Logger log = LoggerFactory.getLogger(MvPplController.class);
	
	@Autowired
	private MvPplService mvPplService;
	
	@Value("${upload.profile.path:/naver-movie-admin/files/profiles}")
	private String profilePath;
	
	@GetMapping("/mvppl/list")
	public String viewMvPplListPage(MvPplVO mvPplVO, Model model) {
		List<MvPplVO> mvPplList = mvPplService.readAllMvPpl(mvPplVO);
		model.addAttribute("mvPplList",mvPplList);
		model.addAttribute("mvPplVO",mvPplVO);
		
		return "mvppl/list";
	}
	
	@GetMapping("/mvppl/prfl/{filename}/")
	public void downloadPrflPctr(@PathVariable String filename,
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
	
	@GetMapping("/mvppl/search")
	public String viewMvPplSearchPage(@RequestParam(required = false) String nm,
									 @RequestParam(required = false) String targetId
									 , Model model) {
		model.addAttribute("nm", nm);
		model.addAttribute("targetId",targetId);
		if( nm != null && nm.length() > 0) {
			List<MvPplVO> pplList = mvPplService.readAllGnrVONoPagenation(nm);
			model.addAttribute("pplList",pplList);
		}
		return "mvppl/search";
	}

}
