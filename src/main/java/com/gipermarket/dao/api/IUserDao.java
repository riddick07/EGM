package com.gipermarket.dao.api;

import java.util.List;

import com.gipermarket.dao.bean.User;

/**
 * DAO class for Table User
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public interface IUserDao {

	/**
	 * Select list of users
	 * 
	 * @return List<String>
	 */
	public List<User> usersList();

	/**
	 * Get user by UserId
	 * 
	 * @param id
	 * @return User
	 */
	public User getUserById(Long id);

	/**
	 * Save user to the end of table
	 * 
	 * @param user
	 * @return User
	 */
	public User insertUser(User user);

	/**
	 * Update user
	 * 
	 * @param user
	 *            - User object
	 * @return User
	 */
	public User updateUser(User user);

	/**
	 * Delete User by user parameters
	 * 
	 * @param user
	 */
	public void deleteUser(User user);

}
