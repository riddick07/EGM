package com.gipermarket.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.gipermarket.services.api.IRegistrationService;
import com.gipermarket.services.bean.dto.RegistrationParametersDto;
import com.gipermarket.services.bean.dto.ValidationDto;
import com.gipermarket.services.impl.RegistrationServiceImpl;
import com.gipermarket.util.Dispatcher;
import com.gipermarket.view.controller.enums.PageParametersEnum;

/**
 * @author DO\dmitry.karpenko
 */
@Controller
public class RegistrationController extends AbstractController {

	private IRegistrationService regService = new RegistrationServiceImpl();

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse resp) throws Exception {
		// HttpSession session = request.getSession(true);
		// SessionHelper sessionHelper = new SessionHelper(session);
		RegistrationParametersDto dto = new RegistrationParametersDto();

		String login = request.getParameter(PageParametersEnum.login.name());
		String password = request.getParameter(PageParametersEnum.password.name());
		String re_password = request.getParameter(PageParametersEnum.retype_password.name());
		String name = request.getParameter(PageParametersEnum.name.name());
		String surname = request.getParameter(PageParametersEnum.surname.name());
		String mail = request.getParameter(PageParametersEnum.mail.name());
		String phone = request.getParameter(PageParametersEnum.phone.name());

		if (!password.equals(re_password))
			return Dispatcher.registrationPage("Passwords are no equals", null);

		dto.setLogin(login);
		dto.setMail(mail);
		dto.setName(name);
		dto.setSurname(surname);
		dto.setPassword(password);
		dto.setPhone(phone);

		ValidationDto validateParameters = regService.validateParameters(dto);

		if (validateParameters.getIsValid()) {
			regService.registrateUser(dto);
			return Dispatcher.loginPage("User is registrated");
		} else {
			return Dispatcher.registrationPage(validateParameters.getMessage(), null);
		}
	}
}
