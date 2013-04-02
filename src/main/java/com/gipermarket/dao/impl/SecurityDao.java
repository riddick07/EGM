package com.gipermarket.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gipermarket.dao.api.ISecurityDao;
import com.gipermarket.dao.bean.Credentials;
import com.gipermarket.util.HibernateUtil;

/**
 * DAO for a access to project
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public class SecurityDao implements ISecurityDao {

	private static final Logger log = Logger.getLogger(SecurityDao.class.getName());

	/**
	 * Select list of credentials
	 * 
	 * @return List<String>
	 */
	@SuppressWarnings("unchecked")
	public List<Credentials> credentialsList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Credentials> credentials = new ArrayList<Credentials>();
		try {
			Transaction transaction = session.beginTransaction();
			credentials = session.createQuery("FROM Credentials").list();
			transaction.commit();
		} catch (HibernateException e) {
			log.info("Select all credentials method exception: " + e);
		}
		return credentials;
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
	 * Get credentials by login
	 * 
	 * @param id
	 * @return Credentials
	 */
	@SuppressWarnings("unchecked")
	public List<Credentials> getCredentialsByLogin(String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Credentials> credentials = new ArrayList<Credentials>();
		try {
			Transaction transaction = session.beginTransaction();
			credentials = session.createQuery("FROM Credentials where login = '" + login + "'").list();
			transaction.commit();
		} catch (HibernateException e) {
			log.info("Select credentialsByLogin method exception: " + e);
		}
		return credentials;
	}

	/**
	 * Save credentials
	 * 
	 * @param credentials
	 * @return Credentials
	 */
	public Credentials insertCredentials(Credentials credentials) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(credentials);
		session.getTransaction().commit();
		session.close();
		return credentials;
	}

	/**
	 * Update credentials
	 * 
	 * @param credentials
	 *            - Credentials object
	 * @return Credentials
	 */
	public Credentials updateCredentials(Credentials credentials) {
		Session session = HibernateUtil.getSessionFactory().openSession();
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
	public void deleteCredentials(Credentials credentials) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(credentials);
		session.getTransaction().commit();
		session.close();
	}
}
