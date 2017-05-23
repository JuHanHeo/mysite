package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		WebUtils.plzLogin(request, response);
		BoardVo vo = new BoardVo();
		
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setUserNo(Integer.parseInt(request.getParameter("no")));
		if("-1".equals(request.getParameter("pgno"))){
			vo.setGroupNo(-1);
		}else{
			vo.setGroupNo(Integer.parseInt(request.getParameter("pgno")));
			vo.setOrderNo(Integer.parseInt(request.getParameter("pono")));
			vo.setDepth(Integer.parseInt(request.getParameter("pdepth")));
		}
		
		if(new BoardDao().insert(vo)){
			WebUtils.redirect("/mysite/board", request, response);
		}
	}

}
