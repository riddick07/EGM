package com.gipermarket.services.impl;

import java.util.logging.Logger;

import com.gipermarket.dao.api.ICredentialsDao;
import com.gipermarket.dao.impl.CredentialsDao;
import com.gipermarket.services.api.IRegistrationService;
import com.gipermarket.services.bean.dto.RegistrationParametersDto;
import com.gipermarket.services.bean.dto.ValidationDto;

/**
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public class RegistrationServiceImpl implements IRegistrationService {

	private static final Logger log = Logger.getLogger(RegistrationServiceImpl.class.getName());

	private ICredentialsDao credentialsDao = new CredentialsDao();

	public ValidationDto validateParameters(RegistrationParametersDto parameters) {
		ValidationDto result = new ValidationDto();

		return result;
	}
}
