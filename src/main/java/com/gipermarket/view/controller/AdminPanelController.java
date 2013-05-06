package com.gipermarket.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.gipermarket.view.controller.enums.PageParametersEnum;

public class AdminPanelController extends GeneralController{

	@Override
	protected ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return new ModelAndView(PageParametersEnum.AdminPanel.name());
	}

}
