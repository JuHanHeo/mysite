package com.jx372.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//@WebFilter("/*")
public class EncodingFilter implements Filter {
	
	private String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("인코딩필터 초기화");
		encoding = fConfig.getInitParameter("encoding");
		if(encoding == null){
			encoding = "UTF-8";
		}
	}

    public EncodingFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//request에 대한 처리
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
		
		//response에 대한 처리
	}

	
}
