package com.ktdsuniversity.admin.mvppl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.admin.mvppl.vo.MvPplVO;

public interface MvPplService {

	public List<MvPplVO> readAllMvPpl(MvPplVO mvPplVO);
	public List<MvPplVO> readAllGnrVONoPagenation(String nm);
	public boolean createOneMvPpl(MvPplVO mvPplVO, MultipartFile uploadFile);
	public boolean updateOneMvPplByMvPplId(MvPplVO mvPplVO, MultipartFile uploadFile);
	public boolean deleteOneMvPplByMvPplId(String mvPplId);
	public boolean deleteMvPplIdBySelectedMvPplId(List<String> mvPplIdList);
	
}
