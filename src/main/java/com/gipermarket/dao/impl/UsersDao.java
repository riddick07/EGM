package com.gipermarket.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gipermarket.dao.bean.User;
import com.gipermarket.util.HibernateUtil;

/**
 * DAO class for Table Users
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public class UsersDao {

	/**
	 * Select list of users
	 * 
	 * @return List<String>
	 */
	@SuppressWarnings("unchecked")
	public List<User> usersList() {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		List<User> users = session.createQuery("FROM Users").list();
		transaction.commit();
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
		Long id = (Long) session.save(user);
		user.setId(id);

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
