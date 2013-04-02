package com.gipermarket.services.impl;

import java.util.List;
import java.util.logging.Logger;

import com.gipermarket.dao.api.ISecurityDao;
import com.gipermarket.dao.bean.Credentials;
import com.gipermarket.dao.impl.SecurityDao;
import com.gipermarket.services.api.ISecurityService;
import com.gipermarket.services.bean.dto.SecurityValidationDto;

/**
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public class SecurityServiceImpl implements ISecurityService {

	private static final Logger log = Logger.getLogger(SecurityServiceImpl.class.getName());

	private ISecurityDao securityDao = new SecurityDao();

	public SecurityValidationDto validateCredentials(String login, String password) {
		SecurityValidationDto dto = new SecurityValidationDto();
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
}
