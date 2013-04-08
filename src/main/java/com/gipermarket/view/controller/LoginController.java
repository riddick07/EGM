package com.gipermarket.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.gipermarket.services.api.ICredentialsService;
import com.gipermarket.services.bean.dto.ValidationDto;
import com.gipermarket.services.impl.CredentialsServiceImpl;
import com.gipermarket.util.Dispatcher;
import com.gipermarket.util.SessionHelper;
import com.gipermarket.view.controller.enums.PageParametersEnum;

/**
 * Controller for Login page. Extends from AbstractController
 * 
 * @author dmitry.karpenko
 */
@Controller
public class LoginController extends AbstractController {

	private ICredentialsService securityService = new CredentialsServiceImpl();

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
			return Dispatcher.loginPage("");
		}

		if (String.valueOf(Boolean.TRUE).equals(request.getParameter(PageParametersEnum.reg.name()))) {
			return Dispatcher.registrationPage("", null);
		}

		if (!sessionHelper.isValid()) {
			String requestUsername = request.getParameter(PageParametersEnum.login.name());
			String requestPassword = request.getParameter(PageParametersEnum.password.name());

			if (requestUsername != null && requestPassword != null) {
				ValidationDto dto = securityService.validateCredentials(requestUsername, requestPassword);
				boolean valid = dto.getIsValid();
				if (valid) {
					sessionHelper.create(requestUsername, requestPassword);
					return Dispatcher.redirectHomePage();
				} else {
					sessionHelper.destroy();
					return Dispatcher.loginPage(dto.getMessage());
				}
			}
		} else {
			return Dispatcher.homePage();
		}
		return Dispatcher.loginPage("");
	}
}
