package com.jx372.mysite.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.GuestBookDao;
import com.jx372.mysite.vo.GuestBookVo;
import com.jx372.web.util.WebUtils;

public class ListAction implements com.jx372.web.action.Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		List<GuestBookVo> list = new GuestBookDao().getList();
		request.setAttribute("list", list);
		List<GuestBookVo> vos = (List<GuestBookVo>)request.getAttribute("list");
		WebUtils.forward("/WEB-INF/views/board/list.jsp", request, response);
		
	}

}
