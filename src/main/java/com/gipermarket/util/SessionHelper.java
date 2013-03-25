package com.gipermarket.util;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import com.gipermarket.domain.UserSession;

public class SessionHelper {
	private static final Logger log = Logger.getLogger(SessionHelper.class.getName());
	public static final String USER_SESSION_ATTRIBUTE_NAME = "user.session";
	private HttpSession session;

	public SessionHelper(HttpSession session) {
		this.session = session;
		if (session == null) {
			log.warning("HTTP session is null");
		}

	}

	/**
	 * 
	 * @param userName
	 * @param password
	 * @param url
	 */
	public void create(String userName, String password, String url) {
		if (session == null) {
			String msg = MessageUtil.getMessage("message.sessionNotCreated");
			log.severe("HTTP session isn't created");
			throw new IllegalStateException(msg);
		}

		UserSession us = new UserSession(userName, password);
		us.setUrl(url);

		session.setAttribute(USER_SESSION_ATTRIBUTE_NAME, us);
	}

	public UserSession getUserSession() {
		return (UserSession) session.getAttribute(USER_SESSION_ATTRIBUTE_NAME);
	}

	public void destroy() {
		if (session != null) {
			session.removeAttribute(USER_SESSION_ATTRIBUTE_NAME);
		}
	}

	public boolean isValid() {
		if (session == null || session.getAttribute(USER_SESSION_ATTRIBUTE_NAME) == null) {
			return false;
		}
		return true;
	}
}
