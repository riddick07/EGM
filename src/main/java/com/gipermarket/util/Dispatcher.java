package com.gipermarket.util;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.web.servlet.ModelAndView;

import com.gipermarket.view.controller.enums.PageNamesEnum;
import com.gipermarket.view.controller.enums.PageParametersEnum;

public class Dispatcher {
	private static final Logger log = Logger.getLogger(Dispatcher.class.getName());

	public static ModelAndView loginPage(String message) {
		log.fine(MessageUtil.getMessage("message.dispatchOnLogin", message));
		Map<String, Object> model = new HashMap<String, Object>();

		model.put(PageParametersEnum.message.name(), message);
		return new ModelAndView(PageParametersEnum.Login.name(), "model", model);
	}

	public static ModelAndView homePage() {
		return new ModelAndView(PageNamesEnum.Home.name());
	}

	public static ModelAndView redirectHomePage() {
		return new ModelAndView("redirect:" + PageNamesEnum.Home.name() + ".vw");
	}
}
