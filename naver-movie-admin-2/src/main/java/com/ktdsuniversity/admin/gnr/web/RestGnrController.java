package com.ktdsuniversity.admin.gnr.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.admin.common.api.exceptions.ApiArgsException;
import com.ktdsuniversity.admin.common.api.vo.ApiResponseVO;
import com.ktdsuniversity.admin.common.api.vo.ApiStatus;
import com.ktdsuniversity.admin.gnr.service.GnrService;
import com.ktdsuniversity.admin.gnr.vo.GnrVO;
import com.ktdsuniversity.admin.mbr.vo.MbrVO;

@RestController
public class RestGnrController {

	private Logger logger = LoggerFactory.getLogger(RestGnrController.class);
	
	@Autowired	
	private GnrService gnrService;
	
	@PostMapping("/api/gnr/create")
	public ApiResponseVO doCreateGnr(GnrVO gnrVO, @SessionAttribute("__ADMIN__") MbrVO mbrVO) {
		String gnrNm = gnrVO.getGnrNm();
		if(gnrNm == null || gnrNm.trim().length() == 0) {
			throw new ApiArgsException(ApiStatus.MISSING_ARGS,"장르명이 누락되었습니다.");
		}
		
		gnrVO.setCrtr(mbrVO.getMbrId());
		gnrVO.setMdfyr(mbrVO.getMbrId());
		
		boolean createResult = gnrService.createOneGnr(gnrVO);
		
		if(createResult) {
			return new ApiResponseVO(ApiStatus.OK);
		}
		else{
			return new ApiResponseVO(ApiStatus.FAIL);
		}
		
	}
	@PostMapping("/api/gnr/update")
	public ApiResponseVO doUpdateGnr(GnrVO gnrVO, @SessionAttribute("__ADMIN__") MbrVO mbrVO) {
		String gnrNm = gnrVO.getGnrNm();
		if(gnrNm == null || gnrNm.trim().length() == 0) {
			throw new ApiArgsException(ApiStatus.MISSING_ARGS,"장르명이 누락되었습니다.");
		}
		
		gnrVO.setMdfyr(mbrVO.getMbrId());
		
		boolean updateResult = gnrService.updateOneGnr(gnrVO);
		
		if(updateResult) {
			return new ApiResponseVO(ApiStatus.OK);
		}
		else {
			return new ApiResponseVO(ApiStatus.FAIL);
		}
	}
	
	@GetMapping("/api/gnr/delete/{gnrId}")
	public ApiResponseVO doDeleteGnr(@PathVariable int gnrId) {
		
		boolean deleteResult = gnrService.deleteOneGnr(gnrId);
		
		if(deleteResult) {
			return new ApiResponseVO(ApiStatus.OK);
		}
		else {
			return new ApiResponseVO(ApiStatus.FAIL);
		}
	}
	@PostMapping("/api/gnr/delete")
	public ApiResponseVO doDeleteGnrBySelectedGnrId(@RequestParam List<Integer> gnrId) {
		boolean deleteResult = gnrService.deleteGnrBySelectedGnrId(gnrId);
		if(deleteResult) {
			return new ApiResponseVO(ApiStatus.OK);
		}else {
			return new ApiResponseVO(ApiStatus.FAIL);
		}
	}
	
}
