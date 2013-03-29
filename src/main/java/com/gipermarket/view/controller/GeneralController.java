package com.gipermarket.view.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.gipermarket.util.Dispatcher;
import com.gipermarket.util.JsonUtil;
import com.gipermarket.util.MessageUtil;
import com.gipermarket.util.SessionHelper;
import com.gipermarket.view.controller.enums.PageParametersEnum;

/**
 * General view controller
 * 
 * @author dmitry.karpenko, sergii.ievliukhin
 */
public abstract class GeneralController extends AbstractController {
	private static final Logger log = Logger.getLogger(GeneralController.class.getName());

	protected String timeoutMsg = "Session timeout, please login again";

	private JsonUtil jsonUtil;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		HttpSession session = req.getSession();
		SessionHelper sessionHelper = new SessionHelper(session);
		if (!sessionHelper.isValid()) {
			log.fine(MessageUtil.getMessage("message.sessionExpired"));
			return returnLoginPage(timeoutMsg);
		}
		ModelAndView processRequest = processRequest(req, resp);
		if (processRequest == null) {
			processRequest = new ModelAndView();
		}

		processRequest.addObject("user", sessionHelper.getUserSession().getLogin());
		return processRequest;
	}

	protected abstract ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception;

	/**
	 * Set session parameters
	 * 
	 * @param session
	 * @param url
	 *            , @param password, @param username is set parameters
	 */
	protected void setSessionAttributes(HttpSession session, String password, String username) {
		assert session != null;
		new SessionHelper(session).create(username, password);
	}

	/**
	 * Removing session parameters
	 * 
	 * @param session
	 */
	protected void removeSessionAttributes(HttpSession session) {
		new SessionHelper(session).destroy();
	}

	/**
	 * Generating model of Login page
	 * 
	 * @param message
	 * @return Login page ModelAndView
	 */
	protected ModelAndView returnLoginPage(String message) {
		return Dispatcher.loginPage(message);
	}

	/**
	 * Generate Home page model
	 * 
	 * @param user
	 *            - user for view
	 * @return Home page ModelAndView
	 */
	protected ModelAndView returnHomePage() {
		return Dispatcher.homePage();
	}

	/**
	 * Set session user name parameter
	 * 
	 * @param user
	 *            - current user name
	 * @return Map <Parameter name, parameter as Object>
	 */
	protected Map<String, Object> setUser(String user) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(PageParametersEnum.loginedUser.name(), user);
		return model;
	}

	public JsonUtil getJsonUtil() {
		return jsonUtil;
	}

	public void setJsonUtil(JsonUtil jsonUtil) {
		this.jsonUtil = jsonUtil;
	}
}
