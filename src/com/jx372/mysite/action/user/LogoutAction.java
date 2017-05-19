package com.jx372.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//로그인 안된 상태의 사용자가 로그아웃에 url로 접근할경우
		HttpSession session = request.getSession();
		
//		중복코드
//		if(session == null){
//			WebUtils.redirect("/mysite/main", request, response);
//			return;
//		}
//
//		UserVo authUser = (UserVo)session.getAttribute("authUser");
//		if(authUser == null){
//			WebUtils.redirect("/mysite/main", request, response);
//			return;
//		}

		//로그아웃 처리
		if(session!=null && session.getAttribute("authUser")!=null){
			session.removeAttribute("authUser");
			session.invalidate(); //세션 아이디가 바뀜
			
		}
		WebUtils.redirect("/mysite/main", request, response);
	}

}
