package com.jx372.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.UserDao;
import com.jx372.mysite.vo.UserVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class JoinAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String passwd = request.getParameter("passwd");
		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setEmail(email);
		vo.setGender(gender);
		vo.setPasswd(passwd);
		
		new UserDao().insert(vo);
		System.out.println(request.getContextPath());
		
		WebUtils.redirect(request.getContextPath()+"/user?a=joinsuccess", request, response);
		
	}

}
