package com.gipermarket.view.controller;


import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.gipermarket.util.Dispatcher;
import com.gipermarket.util.MessageUtil;
import com.gipermarket.util.SessionHelper;
import com.gipermarket.view.controller.enums.PageParametersEnum;

/**
 * Controller for Login page. Extends from AbstractController
 * 
 * @author dmitry.karpenko
 * 
 */
@Controller
public class LoginController extends AbstractController {
	
	//TODO: Add checkers for logging
	private static final Logger log = Logger.getLogger(LoginController.class.getName());

	/**
	 * Controller for returned Login page and validating credentials
	 * 
	 * @param request
	 *            - HttpServletRequest
	 * @param response
	 *            - HttpServletResponse
	 * @return ModelAndView page
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		SessionHelper sessionHelper = new SessionHelper(session);

		if (String.valueOf(Boolean.TRUE).equals(request.getParameter(PageParametersEnum.logout.name()))) {
			sessionHelper.destroy();
			return new ModelAndView(PageParametersEnum.Login.name());
		}

		if (!sessionHelper.isValid()) {
			/** User name and password from parameter request **/
			String requestUsername = request.getParameter(PageParametersEnum.login.name());
			String requestPassword = request.getParameter(PageParametersEnum.password.name());
			
			if (requestUsername != null && requestPassword != null) {
				boolean valid = true;// TODO:Create validation
				if (valid) {
					sessionHelper.create(requestUsername, requestPassword);
					return Dispatcher.redirectHomePage();
				} else {
					sessionHelper.destroy();
					return Dispatcher.loginPage(MessageUtil.getMessage("message.wrongCredentials"));
				}
			}
		} else {
			return Dispatcher.homePage();
		}
		return Dispatcher.loginPage("");
	}
}
