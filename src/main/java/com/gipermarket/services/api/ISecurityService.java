package com.gipermarket.services.api;

import com.gipermarket.services.bean.dto.SecurityValidationDto;

/**
 * 
 * @author DO\dmitry.karpenko
 *
 */
public interface ISecurityService {
	
	public SecurityValidationDto validateCredentials(String login, String password);
	
}
