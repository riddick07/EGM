package com.gipermarket.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gipermarket.dao.bean.Credentials;
import com.gipermarket.util.HibernateUtil;

/**
 * DAO for a access to project
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public class SecurityDao implements Serializable {

	private static final Logger log = Logger.getLogger(SecurityDao.class.getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8163391763545680396L;

	/**
	 * Select list of users
	 * 
	 * @return List<String>
	 */
	@SuppressWarnings("unchecked")
	public List<Credentials> credentialsList() {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Credentials> allCredentials = new ArrayList<Credentials>();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			allCredentials = session.createQuery("FROM Credentials").list();
			transaction.commit();
			
		} catch (HibernateException e) {
			log.info("Selecte all credentials exception: " + e);
		}
		return allCredentials;
	}

	/**
	 * Get credentials by id
	 * 
	 * @param id
	 * @return Credentials
	 */
	public Credentials getCredentialsById(Long id) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		Credentials user = (Credentials) session.get(Credentials.class, id);
		session.close();
		return user;
	}

	/**
	 * Save credentials
	 * 
	 * @param credentials
	 * @return Credentials
	 */
	public Credentials insertCredentials(Credentials credentials) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Long id = (Long) session.save(credentials);
		credentials.setId(id);

		session.getTransaction().commit();
		session.close();

		return credentials;
	}

	/**
	 * Update user
	 * 
	 * @param credentials
	 *            - Credentials object
	 * @return Credentials
	 */
	public Credentials updateCredentials(Credentials credentials) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.merge(credentials);
		session.getTransaction().commit();
		session.close();
		return credentials;
	}

	/**
	 * Delete Credentials by parameters
	 * 
	 * @param credentials
	 */
	public void deleteUserCredentials(Credentials credentials) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(credentials);
		session.getTransaction().commit();
		session.close();
	}

}
