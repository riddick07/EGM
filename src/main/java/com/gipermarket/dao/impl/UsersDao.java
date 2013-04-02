package com.gipermarket.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gipermarket.dao.api.IUserDao;
import com.gipermarket.dao.bean.User;
import com.gipermarket.util.HibernateUtil;

/**
 * DAO class for Table User
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public class UsersDao implements IUserDao{

	private static final Logger log = Logger.getLogger(UsersDao.class.getName());

	/**
	 * Select list of users
	 * 
	 * @return List<String>
	 */
	@SuppressWarnings("unchecked")
	public List<User> usersList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<User> users = new ArrayList<User>();
		try {
			Transaction transaction = session.beginTransaction();
			users = session.createQuery("FROM User").list();
			transaction.commit();
		} catch (HibernateException e) {
			log.info("Select all credentials method exception: " + e);
		}
		return users;
	}

	/**
	 * Get user by UserId
	 * 
	 * @param id
	 * @return User
	 */
	public User getUserById(Long id) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		User user = (User) session.get(User.class, id);
		session.close();
		return user;
	}

	/**
	 * Save user to the end of table
	 * 
	 * @param user
	 * @return User
	 */
	public User insertUser(User user) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(user);

		session.getTransaction().commit();
		session.close();

		return user;
	}

	/**
	 * Update user
	 * 
	 * @param user
	 *            - User object
	 * @return User
	 */
	public User updateUser(User user) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.merge(user);
		session.getTransaction().commit();
		session.close();
		return user;
	}

	/**
	 * Delete User by user parameters
	 * 
	 * @param user
	 */
	public void deleteUser(User user) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
		session.close();
	}
}
