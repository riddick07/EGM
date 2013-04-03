package com.gipermarket.dao.api;

import java.util.List;

import com.gipermarket.dao.bean.Credentials;

/**
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public interface ISecurityDao {

	/**
	 * Select list of credentials
	 * 
	 * @return List<String>
	 */
	public List<Credentials> credentialsList();

	/**
	 * Get credentials by id
	 * 
	 * @param id
	 * @return Credentials
	 */
	public Credentials getCredentialsById(Long id);

	/**
	 * Get credentials by login
	 * 
	 * @param id
	 * @return Credentials
	 */
	public List<Credentials> getCredentialsByLogin(String login);

	/**
	 * Save credentials
	 * 
	 * @param credentials
	 * @return Credentials
	 */
	public Credentials insertCredentials(Credentials credentials);

	/**
	 * Update credentials
	 * 
	 * @param credentials
	 *            - Credentials object
	 * @return Credentials
	 */
	public Credentials updateCredentials(Credentials credentials);

	/**
	 * Delete Credentials by parameters
	 * 
	 * @param credentials
	 */
	public void deleteCredentials(Credentials credentials);

}
