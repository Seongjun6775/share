package com.ktdsuniversity.admin.mvppl.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.admin.common.api.vo.ApiResponseVO;
import com.ktdsuniversity.admin.common.api.vo.ApiStatus;
import com.ktdsuniversity.admin.mbr.vo.MbrVO;
import com.ktdsuniversity.admin.mvppl.service.MvPplService;
import com.ktdsuniversity.admin.mvppl.vo.MvPplVO;

@RestController
public class RestMvPplController {
	
	private static final Logger log = LoggerFactory.getLogger(RestMvPplController.class);

	@Autowired
	private MvPplService mvPplService;
	
	@PostMapping("/api/mvppl/create")
	public ApiResponseVO doCreateMvPpl(MvPplVO mvPplVO,
									   @SessionAttribute("__ADMIN__") MbrVO mbrVO, MultipartFile uploadFile) {
		
		mvPplVO.setCrtr(mbrVO.getMbrId());
		mvPplVO.setMdfyr(mbrVO.getMbrId());

		boolean isSuccess = mvPplService.createOneMvPpl(mvPplVO, uploadFile);
		if(isSuccess) {
			return new ApiResponseVO(ApiStatus.OK);
		}
		else {
			return new ApiResponseVO(ApiStatus.FAIL);
		}
	}
	@PostMapping("/api/mvppl/update")
	public ApiResponseVO doUpdateMvPpl(MvPplVO mvPplVO,
			@SessionAttribute("__ADMIN__") MbrVO mbrVO, MultipartFile uploadFile) {
		
		mvPplVO.setMdfyr(mbrVO.getMbrId());
		
		boolean isSuccess = mvPplService.updateOneMvPplByMvPplId(mvPplVO, uploadFile);
		if(isSuccess) {
			return new ApiResponseVO(ApiStatus.OK);
		}
		else {
			return new ApiResponseVO(ApiStatus.FAIL);
		}
	}
	
}
