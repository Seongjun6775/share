package com.ktdsuniversity.admin.mbrlgnhist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.admin.mbrlgnhist.dao.MbrLgnHistDAO;
import com.ktdsuniversity.admin.mbrlgnhist.vo.MbrLgnHistVO;

@Service
public class MbrLgnHistServiceImpl implements MbrLgnHistService {

	@Autowired
	private MbrLgnHistDAO mbrLgnHistDAO;
	
	@Override
	public boolean createMbrLgnHist(MbrLgnHistVO mbrLgnHistVO) {
		return mbrLgnHistDAO.createMbrLgnHist(mbrLgnHistVO) > 0;
	}

}
