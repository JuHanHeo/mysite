package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		WebUtils.plzLogin(request, response);
		BoardVo vo =  new BoardDao().get(Integer.parseInt(request.getParameter("no")));
		request.setAttribute("vo", vo);
		WebUtils.forward("/WEB-INF/views/board/modify.jsp", request, response);
	}

}
