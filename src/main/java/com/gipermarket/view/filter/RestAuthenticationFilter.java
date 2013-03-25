package com.gipermarket.view.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gipermarket.util.SessionHelper;

/**
 * 
 * A RestAuthenticationFilter is class for filtering *.dt requests
 * 
 * @author oleg.topchiy
 */
public class RestAuthenticationFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * Method, that checks, if session is still valid. If not, returns 401 status code
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session = req.getSession();
		SessionHelper sessionHelper = new SessionHelper(session);

		if (!sessionHelper.isValid()) {
			HttpServletResponse hsr = (HttpServletResponse) response;
			hsr.setStatus(401);
			hsr.getWriter().print(req.getContextPath());
			response = hsr;
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}

}
