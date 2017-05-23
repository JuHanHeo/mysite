package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class DeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		WebUtils.plzLogin(request, response);
		int no = Integer.parseInt(request.getParameter("no"));
		boolean bool = new BoardDao().delete(no);
		if(bool){
			WebUtils.redirect("/mysite/board", request, response);
			return;
		}
	}

}
