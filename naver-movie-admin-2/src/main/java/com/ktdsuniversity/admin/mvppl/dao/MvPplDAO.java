package com.ktdsuniversity.admin.mvppl.dao;

import java.util.List;

import com.ktdsuniversity.admin.mvppl.vo.MvPplVO;

public interface MvPplDAO {

	public List<MvPplVO> readAllMvPpl(MvPplVO mvPplVO);
	public List<MvPplVO> readAllGnrVONoPagenation(String nm);
	public MvPplVO readOneMvPplVOByMvPplId(String mvPplId);
	public int createOneMvPpl(MvPplVO mvPplVO);
	public int updateOneMvPplByMvPplId(MvPplVO mvPplVO);
	public int deleteOneMvPplByMvPplId(String mvPplId);
	public int deleteMvPplIdBySelectedMvPplId(List<String> mvPplIdList);
	
}
