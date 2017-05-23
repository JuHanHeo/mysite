package com.jx372.web.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class WebUtils {
	public static void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}
	public static void redirect(String path, HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.sendRedirect(path);
	}
	public static void plzLogin(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		
		if(session.getAttribute("authUser")==null){
			try {
				redirect("/mysite/user?a=loginform", request, response);
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}
}
