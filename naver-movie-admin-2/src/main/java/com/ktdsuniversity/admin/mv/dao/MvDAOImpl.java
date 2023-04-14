package com.ktdsuniversity.admin.mv.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.mv.vo.MvVO;

@Repository
public class MvDAOImpl extends SqlSessionDaoSupport implements MvDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int createNewMv(MvVO mvVO) {
		return getSqlSession().insert("Mv.createNewMv", mvVO);
	}

	@Override
	public int updateOneMv(MvVO mvVO) {
		return getSqlSession().update("Mv.updateOneMv", mvVO);
	}

	@Override
	public int deleteOneMV(String mvId) {
		return getSqlSession().update("Mv.deleteOneMV", mvId);
	}

	@Override
	public int deleteMvByMvIdList(List<String> mvIdList) {
		return getSqlSession().update("Mv.deleteMvByMvIdList", mvIdList);
	}

	@Override
	public MvVO readOneMvById(String mvId) {
		return getSqlSession().selectOne("Mv.readOneMvById", mvId);
	}

	@Override
	public List<MvVO> readAllMv(MvVO mvVO) {
		return getSqlSession().selectList("Mv.readAllMv", mvVO);
	}
}
