package com.ktdsuniversity.admin.mvppl.service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.admin.common.api.exceptions.ApiException;
import com.ktdsuniversity.admin.common.api.vo.ApiStatus;
import com.ktdsuniversity.admin.mvppl.dao.MvPplDAO;
import com.ktdsuniversity.admin.mvppl.vo.MvPplVO;

@Service
public class MvPplServiceImpl implements MvPplService {

	private static final Logger log = LoggerFactory.getLogger(MvPplServiceImpl.class);
	
	@Autowired
	private MvPplDAO mvPplDAO;

	@Value("${upload.profile.path:/naver-movie-admin/files/profiles}")
	private String profilePath;
	
	@Override
	public List<MvPplVO> readAllMvPpl(MvPplVO mvPplVO) {
		// TODO
		//Calendar
		// startDt가 비어있을 경우, 현재일의 한달전 날짜를 가져와서 세팅한다.
		if(mvPplVO.getStartDt() == null || mvPplVO.getStartDt().length() == 0) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1); // 한 달 빼기, 윤달 계산도 가능!!
			// 연도
			int year = cal.get(Calendar.YEAR);
			// 월
			int month = cal.get(Calendar.MONTH) + 1; // 0~11월까지 가지고 있다.
			// 일
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			// 결과값 2023-4-4 -> 2023-04-04 변경해주어야 한다. DB때문에
			// 4 -> 04, 10 -> 10
			String strMonth = month < 10 ? "0" + month : month + "";
			String strDay = day < 10 ? "0" + day : day + "";
			
			String startDt = year + "-" + strMonth + "-" + strDay;
			mvPplVO.setStartDt(startDt);
		}
		// endDt가 비어있을 경우, 현재일을 가져와 세팅한다.
		if(mvPplVO.getEndDt() == null || mvPplVO.getEndDt().length() == 0) {
			Calendar cal = Calendar.getInstance();
			// 연도
			int year = cal.get(Calendar.YEAR);
			// 월
			int month = cal.get(Calendar.MONTH) + 1; // 0~11월까지 가지고 있다.
			// 일
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			// 결과값 2023-4-4 -> 2023-04-04 변경해주어야 한다. DB때문에
			// 4 -> 04, 10 -> 10
			String strMonth = month < 10 ? "0" + month : month + "";
			String strDay = day < 10 ? "0" + day : day + "";
			
			String endDt = year + "-" + strMonth + "-" + strDay;
			mvPplVO.setEndDt(endDt);
		}
		
		return mvPplDAO.readAllMvPpl(mvPplVO);
	}

	@Override
	public List<MvPplVO> readAllGnrVONoPagenation(String nm) {
		return mvPplDAO.readAllGnrVONoPagenation(nm);
	}
	
	@Override
	public boolean createOneMvPpl(MvPplVO mvPplVO, MultipartFile uploadFile) {
		
		if(uploadFile != null && !uploadFile.isEmpty()) {
			
			File dir = new File(profilePath);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			String uuidFileName = UUID.randomUUID().toString();
			File profileFile = new File(dir, uuidFileName);
			try {
				uploadFile.transferTo(profileFile);
			} catch (IllegalStateException | IOException e) {
				log.error(e.getMessage(), e);
			}
			mvPplVO.setPrflPctr(uuidFileName);
		}
		return mvPplDAO.createOneMvPpl(mvPplVO) > 0;
	}

	@Override
	public boolean updateOneMvPplByMvPplId(MvPplVO mvPplVO, MultipartFile uploadFile) {
		
		if(uploadFile != null && !uploadFile.isEmpty()) {
			
			File dir = new File(profilePath);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			String uuidFileName = UUID.randomUUID().toString();
			File profileFile = new File(dir, uuidFileName);
			try {
				uploadFile.transferTo(profileFile);
			} catch (IllegalStateException | IOException e) {
				log.error(e.getMessage(), e);
			}
			mvPplVO.setPrflPctr(uuidFileName);
		}
		
		boolean isModify = false;
		MvPplVO orignalMvPplData = mvPplDAO.readOneMvPplVOByMvPplId(mvPplVO.getMvPplId());
		MvPplVO updateMvPplVO = new MvPplVO();
		updateMvPplVO.setMvPplId(mvPplVO.getMvPplId());
		updateMvPplVO.setRlNm(mvPplVO.getRlNm());
		updateMvPplVO.setMdfyr(mvPplVO.getMdfyr());
		
		if((mvPplVO.getPrflPctr() == null || mvPplVO.getPrflPctr().length() == 0) 
				&& mvPplVO.getIsDeletePctr().equals("N")) {
			updateMvPplVO.setPrflPctr(orignalMvPplData.getPrflPctr());
		}else {
			isModify=true;
			updateMvPplVO.setPrflPctr(mvPplVO.getPrflPctr());
		}
		
		if(!orignalMvPplData.getNm().equals(mvPplVO.getNm())) {
			isModify = true;
			updateMvPplVO.setNm(mvPplVO.getNm());
		}
		String rlNm = orignalMvPplData.getRlNm();
		if(rlNm == null) {
			rlNm="";
		}
		if(!rlNm.equals(mvPplVO.getRlNm())) {
			isModify = true;
			updateMvPplVO.setRlNm(rlNm);
		}
		String requestUseYn = mvPplVO.getUseYn() == null || mvPplVO.getUseYn().length()==0 ? "N" : mvPplVO.getUseYn();
		if(!orignalMvPplData.getUseYn().equals(requestUseYn)) {
			isModify = true;
			updateMvPplVO.setUseYn(mvPplVO.getUseYn());
		}
		if(isModify) {
			return mvPplDAO.updateOneMvPplByMvPplId(updateMvPplVO) > 0;
		}else {
			throw new ApiException(ApiStatus.MISSING_ARGS, "변경된 정보가 없습니다.");
		}
	}

	@Override
	public boolean deleteOneMvPplByMvPplId(String mvPplId) {
		return mvPplDAO.deleteOneMvPplByMvPplId(mvPplId) > 0;
	}

	@Override
	public boolean deleteMvPplIdBySelectedMvPplId(List<String> mvPplIdList) {
		
		int delCount = mvPplDAO.deleteMvPplIdBySelectedMvPplId(mvPplIdList);
		boolean isSuccess = delCount == mvPplIdList.size();
		
		if(!isSuccess) {
			throw new ApiException(ApiStatus.FAIL,"삭제에 실패했습니다. 요청건수: ("+mvPplIdList.size()+"건), 삭제건수 ("+delCount+"건)" );
		}
		
		return delCount > 0;
	}
	
}
