package com.gipermarket.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.gipermarket.dao.api.ICredentialsDao;
import com.gipermarket.dao.api.IUserDao;
import com.gipermarket.dao.bean.Credentials;
import com.gipermarket.dao.bean.User;
import com.gipermarket.dao.impl.CredentialsDao;
import com.gipermarket.dao.impl.UserDao;
import com.gipermarket.services.api.IRegistrationService;
import com.gipermarket.services.bean.dto.RegistrationParametersDto;
import com.gipermarket.services.bean.dto.ValidationDto;

/**
 * @author DO\dmitry.karpenko
 */
public class RegistrationServiceImpl implements IRegistrationService {

	private static final Logger log = Logger.getLogger(RegistrationServiceImpl.class.getName());

	private ICredentialsDao credentialsDao = new CredentialsDao();

	private IUserDao userDao = new UserDao();

	/**
	 * Validate registration parameters
	 * 
	 * @param parameters
	 *            RegistrationParametersDto
	 * @return ValidationDto
	 */
	public ValidationDto validateParameters(RegistrationParametersDto parameters) {
		ValidationDto result = new ValidationDto();
		result.setIsValid(true);

		if (parameters.getLogin() == null) {
			result = falseResult("Login is can't be empty");
		}

		List<Credentials> credentialses = credentialsDao.credentialsList();
		Map<String, String> credentialsMap = new HashMap<String, String>();
		for (Credentials cr : credentialses) {
			credentialsMap.put(cr.getLogin(), cr.getPassword());
		}
		if (credentialsMap.keySet().contains(parameters.getLogin())) {
			result = falseResult("Login is already exist");
		}

		if (parameters.getLogin() == null || parameters.getPassword() == null || parameters.getName() == null) {
			result = falseResult("Required fields are can't be empty");
		}

		if (parameters.getPassword().length() < 6) {
			result = falseResult("Password is too short");
		}
		if (result.getMessage() != null) {
			log.info(result.getMessage());
		}
		return result;
	}

	/**
	 * Insert user credentials and information into DB
	 * 
	 * @param parameters
	 *            RegistrationParametersDto
	 */
	public void registrateUser(RegistrationParametersDto parameters) {
		User user = new User();
		Credentials credentials = new Credentials();
		credentials.setLogin(parameters.getLogin());
		credentials.setPassword(parameters.getPassword());

		user.setName(parameters.getName());
		user.setMail(parameters.getMail());
		user.setPhone(parameters.getPhone());
		user.setSurname(parameters.getSurname());
		user.setRegistrationDate();

		User insertedUser = userDao.insertUser(user);
		credentials.setUserId(insertedUser.getId());
		credentialsDao.insertCredentials(credentials);
	}

	/**
	 * Generate ValidationDto with false valid
	 * 
	 * @param msg
	 *            message
	 * @return ValidationDto with false valid
	 */
	private ValidationDto falseResult(String msg) {
		ValidationDto result = new ValidationDto();
		result.setIsValid(false);
		result.setMessage(msg);
		return result;
	}
}
