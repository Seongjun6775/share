package com.ktdsuniversity.admin.mvgnr.dao;

import com.ktdsuniversity.admin.mvgnr.vo.MvGnrVO;

public interface MvGnrDAO {
	public int createNewMvGnr(MvGnrVO mvGnrVO);
	//업데이트는 하나하나 체크하기에는 코드가 길어지기 때문에 삭제 후 생성으로 업데이트 대체
	public int deleteOneMvGnrByMvGnrIdAndMvId(MvGnrVO mvGnrVO);
}
