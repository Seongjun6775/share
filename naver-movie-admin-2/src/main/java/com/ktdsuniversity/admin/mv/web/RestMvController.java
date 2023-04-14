package com.ktdsuniversity.admin.mv.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.admin.common.api.vo.ApiResponseVO;
import com.ktdsuniversity.admin.common.api.vo.ApiStatus;
import com.ktdsuniversity.admin.mbr.vo.MbrVO;
import com.ktdsuniversity.admin.mv.service.MvService;
import com.ktdsuniversity.admin.mv.vo.MvVO;

@RestController
public class RestMvController {
	
	@Autowired
	private MvService mvService;
	
	@PostMapping("/api/mv/create")
	public ApiResponseVO createNewMv(MvVO mvVO, MultipartFile uploadFile, @SessionAttribute("__ADMIN__")MbrVO mbrVO) {
		
		mvVO.setCrtr(mbrVO.getMbrId());
		mvVO.setMdfyr(mbrVO.getMbrId());
		boolean createResult = mvService.createNewMv(mvVO, uploadFile);
		if(createResult) {
			return new ApiResponseVO(ApiStatus.OK, "/mv/detail/" + mvVO.getMvId());
		}else {
			return new ApiResponseVO(ApiStatus.FAIL);
		}
	}
	@PostMapping("/api/mv/update")
	public ApiResponseVO updateNewMv(MvVO mvVO, MultipartFile uploadFile, @SessionAttribute("__ADMIN__")MbrVO mbrVO) {
		
		mvVO.setCrtr(mbrVO.getMbrId());
		mvVO.setMdfyr(mbrVO.getMbrId());
		boolean updateResult = mvService.updateOneMv(mvVO, uploadFile);
		if(updateResult) {
			return new ApiResponseVO(ApiStatus.OK, "/mv/detail/" + mvVO.getMvId());
		}else {
			return new ApiResponseVO(ApiStatus.FAIL);
		}
	}
}
