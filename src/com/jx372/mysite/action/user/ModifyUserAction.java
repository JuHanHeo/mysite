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

public class ModifyUserAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String passwd = request.getParameter("passwd");
		
		UserVo vo = new UserVo();
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setGender(gender);
		if(passwd != null){
			vo.setPasswd(passwd);
		}
		
		new UserDao().update(vo);
		
		HttpSession session = request.getSession();
		((UserVo)session.getAttribute("authUser")).setName(name);
//		authUser.setName(name);
		
		WebUtils.redirect("/mysite/main", request, response);
//		WebUtils.forward("/WEB-INF/views/user/modifyform.jsp", request, response);
		
		
	}

}
