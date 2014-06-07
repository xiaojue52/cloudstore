package com.project.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * …Ë÷√«Î«Û±‡¬ÎŒ™UTF-8
 * @author Administrator
 *
 */
public class EncodeFilter implements Filter {

	private String encoding = "";
	private boolean enable = false;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		if (this.enable) {
			request.setCharacterEncoding(this.encoding);
		}
		chain.doFilter(request, response);
		//System.out.print(this.encoding);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		this.encoding = arg0.getInitParameter("encoding");
		String able = arg0.getInitParameter("enable");
		if (able.equals("true")) {
			this.enable = true;
		}
	}
}
