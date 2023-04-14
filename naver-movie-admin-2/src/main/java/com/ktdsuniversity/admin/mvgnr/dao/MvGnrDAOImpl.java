package com.ktdsuniversity.admin.mvgnr.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.mvgnr.vo.MvGnrVO;

@Repository
public class MvGnrDAOImpl extends SqlSessionDaoSupport implements MvGnrDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int createNewMvGnr(MvGnrVO mvGnrVO) {
		return getSqlSession().insert("MvGnr.createNewMvGnr",mvGnrVO);
	}

	@Override
	public int deleteOneMvGnrByMvGnrIdAndMvId(MvGnrVO mvGnrVO) {
		return getSqlSession().update("MvGnr.deleteOneMvGnrByMvGnrIdAndMvId", mvGnrVO);
	}
}
