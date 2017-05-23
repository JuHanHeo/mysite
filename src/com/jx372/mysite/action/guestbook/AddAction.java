package com.jx372.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.GuestBookDao;
import com.jx372.mysite.vo.GuestBookVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class AddAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		GuestBookVo vo = new GuestBookVo();
		vo.setName(request.getParameter("name"));
		vo.setMessage(request.getParameter("message"));
		vo.setPasswd(request.getParameter("passwd"));
		new GuestBookDao().insert(vo);
		
		
		WebUtils.redirect("/mysite/guestbook", request, response);
	}

}
