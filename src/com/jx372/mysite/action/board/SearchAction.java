package com.jx372.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String kwd = request.getParameter("kwd");
		String start = request.getParameter("p");
		List<BoardVo> list = new BoardDao().getList(start, kwd);
		request.setAttribute("list", list);
		request.setAttribute("kwd", kwd);
		WebUtils.forward("/WEB-INF/views/board/list.jsp", request, response);

	}

}
