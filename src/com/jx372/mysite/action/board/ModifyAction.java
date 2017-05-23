package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;
import com.sun.corba.se.impl.protocol.BootstrapServerRequestDispatcher;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		WebUtils.plzLogin(request, response);
		BoardVo vo = new BoardVo();
		String sno = request.getParameter("no");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int no = Integer.parseInt(sno);
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		
		
		new BoardDao().update(vo);
		
		WebUtils.redirect("/mysite/board?a=view&no="+no, request, response);
		
		
	}

}
