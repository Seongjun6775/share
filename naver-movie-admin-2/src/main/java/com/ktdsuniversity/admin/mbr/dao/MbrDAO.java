package com.ktdsuniversity.admin.mbr.dao;

import java.util.List;

import com.ktdsuniversity.admin.mbr.vo.MbrVO;

public interface MbrDAO {
	
	public int readCountMbrById(String mbrId);
	public String readSaltMbrById(String mbrId);
	public String readLgnBlockYnById(String mbrId);
	
	public MbrVO readOneMbrByIdAndPwd(MbrVO mbrVO);
	
	public int updateMbrLgnSucc(MbrVO mbrVO);
	public int updateMbrLgnFail(MbrVO mbrVO);
	public int updateMbrLgnBlock(MbrVO mbrVO);
	
	public List<MbrVO> readAllAdminMbr();
	
	public int createNewAdminMbr(MbrVO mbrVO);
	public int updateOneAdminMbr(MbrVO mbrVO);
	public int deleteOneAdminMbr(String mbrId);
	
}
