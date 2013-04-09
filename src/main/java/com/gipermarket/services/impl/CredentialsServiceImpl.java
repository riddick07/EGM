package com.gipermarket.services.impl;

import java.util.List;
import java.util.logging.Logger;

import com.gipermarket.dao.api.ICredentialsDao;
import com.gipermarket.dao.bean.Credentials;
import com.gipermarket.dao.impl.CredentialsDao;
import com.gipermarket.services.api.ICredentialsService;
import com.gipermarket.services.bean.dto.ValidationDto;

/**
 * Service for credentials
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public class CredentialsServiceImpl implements ICredentialsService {

	private static final Logger log = Logger.getLogger(CredentialsServiceImpl.class.getName());

	private ICredentialsDao securityDao = new CredentialsDao();

	public ValidationDto validateCredentials(String login, String password) {
		ValidationDto dto = new ValidationDto();
		List<Credentials> credentialsByLogin = securityDao.getCredentialsByLogin(login);

		if (credentialsByLogin == null || credentialsByLogin.size() < 1) {
			dto.setIsValid(false);
			String message = "Login is false. Storage have not current login";
			dto.setMessage(message);
			log.info(message);
		}
		if (credentialsByLogin.size() > 0) {
			String pass = credentialsByLogin.get(0).getPassword();
			if (pass.equals(password)) {
				dto.setIsValid(true);
				dto.setRole(credentialsByLogin.get(0).getRole());
				dto.setMessage("");
				log.info("Logined as " + login + ".");
			} else {
				dto.setIsValid(false);
				String message = "Login is false. Wrong password";
				dto.setMessage(message);
				log.info(message);
			}
		}
		return dto;
	}

	public List<String> listOfRoles() {
		List<String> roles = securityDao.getRoles();
		return roles;
	}
}
