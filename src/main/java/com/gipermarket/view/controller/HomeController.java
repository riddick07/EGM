package com.gipermarket.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;


/**
 * Controller for Home page
 * 
 * @author dmitry.karpenko
 * 
 */
public class HomeController extends GeneralController {
	@Override
	protected ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return returnHomePage();
	}
}
