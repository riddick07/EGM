package com.gipermarket.services.api;

import com.gipermarket.services.bean.dto.RegistrationParametersDto;
import com.gipermarket.services.bean.dto.ValidationDto;

/**
 * 
 * @author DO\dmitry.karpenko
 *
 */
public interface IRegistrationService {
	
	public ValidationDto validateParameters(RegistrationParametersDto parameters);
	
	public void registrateUser(RegistrationParametersDto parameters);
	
}
