package com.jx372.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.dao.UserDao;
import com.jx372.mysite.vo.UserVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		UserVo vo = new UserDao().get(email, passwd);
		
		if(vo == null){
			//로그인 실패한경우 (리다이렉트 방식)
//			WebUtils.redirect(request.getContextPath()+"/user?a=loginform&result=fail", request, response);
			//(포워드 방식)
			
			request.setAttribute("result", "fail");
			WebUtils.forward("/WEB-INF/views/user/loginform.jsp", request, response);
			
			return; //헤더는 위에서 나갔다 밑에서 또 나가는 경우가 있을경우 중복해서 나가면 에러 난다. 여기서 확실히 종료 시켜주어야 한다.
		}else{
			//로그인 성공, 인증 처리
			HttpSession session = request.getSession(true); //true(세션 테이블에 아이디가 없으면 만들어서 줘)
			session.setAttribute("authUser", vo); // authuser 이름으로 vo객체 정보를 저장
			
			//main redirect
			WebUtils.redirect("/mysite/main", request, response);
		}
		
	}

}
