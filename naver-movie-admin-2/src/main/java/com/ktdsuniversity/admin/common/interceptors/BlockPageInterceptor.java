package com.ktdsuniversity.admin.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktdsuniversity.admin.mbr.vo.MbrVO;

public class BlockPageInterceptor extends HandlerInterceptorAdapter{

	private Logger log = LoggerFactory.getLogger(BlockPageInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("block인터셉터 {}");
		HttpSession session = request.getSession();
		MbrVO member = (MbrVO)session.getAttribute("__ADMIN__");
		
		//세션이 없는지 체크.
		//세션이 있다 = return false;
		if(member != null) {
			log.info("로그인 했으면 이리로 가!");
			response.sendRedirect(request.getContextPath() + "/mbr/list");
			return false;
		}
		
		//세션이 없다 = return true;
		
		log.info("로그인 안했구나");
		return true;
	}
	
	
}
