package com.gipermarket.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.gipermarket.domain.UserSession;
import com.gipermarket.util.Dispatcher;
import com.gipermarket.util.SessionHelper;


/**
 * Controller for Home page
 * 
 * @author dmitry.karpenko
 * 
 */
public class HomeController extends GeneralController {
	@Override
	protected ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession(true);
        SessionHelper sessionHelper = new SessionHelper(session);
        UserSession userSession = sessionHelper.getUserSession();
        
		return Dispatcher.homePage(userSession.getRole());
	}
}
