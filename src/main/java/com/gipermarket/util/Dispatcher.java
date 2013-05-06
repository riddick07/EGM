package com.gipermarket.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.web.servlet.ModelAndView;

import com.gipermarket.dao.enums.RolesEnum;
import com.gipermarket.view.controller.enums.PageNamesEnum;
import com.gipermarket.view.controller.enums.PageParametersEnum;

/**
 *
 */
public class Dispatcher {
	private static final Logger log = Logger.getLogger(Dispatcher.class.getName());

	public static ModelAndView loginPage(String message, List<String> roles) {
		log.fine(MessageUtil.getMessage("message.dispatchOnLogin", message));
		Map<String, Object> model = new HashMap<String, Object>();
		if (roles == null) {
			roles = new ArrayList<String>();
			roles.add(RolesEnum.BUYER.getName());
			roles.add(RolesEnum.GUEST.getName());
		}
		model.put(PageParametersEnum.roles.name(), roles);
		model.put(PageParametersEnum.message.name(), message);
		return new ModelAndView(PageParametersEnum.Login.name(), "model", model);
	}

	public static ModelAndView registrationPage(String message, List<String> roles) {
		Map<String, Object> model = new HashMap<String, Object>();

		if (roles == null) {
			roles = new ArrayList<String>();
			roles.add(RolesEnum.BUYER.getName());
			roles.add(RolesEnum.GUEST.getName());
		}

		model.put(PageParametersEnum.message.name(), message);
		model.put(PageParametersEnum.roles.name(), roles);

		return new ModelAndView(PageParametersEnum.Registration.name(), "model", model);
	}

	public static ModelAndView homePage(RolesEnum role) {
		Map<String, Object> model = new HashMap<String, Object>();

		if (role == null)// TODO: Change on logging and exception. Now its trick
			role = RolesEnum.ADMINISTRATOR;

		model.put(PageParametersEnum.role.name(), role.getName());
		return new ModelAndView(PageNamesEnum.Home.name(), "model", model);
	}

	public static ModelAndView redirectHomePage() {
		return new ModelAndView("redirect:" + PageNamesEnum.Home.name() + ".vw");
	}
}
