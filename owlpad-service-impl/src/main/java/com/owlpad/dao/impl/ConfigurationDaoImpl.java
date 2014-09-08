package com.owlpad.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.owlpad.dao.ConfigurationDao;
import com.owlpad.domain.configuration.Configuration;

/**
 * 
 * @author Jay Paulynice
 *
 */
@Repository
public class ConfigurationDaoImpl implements ConfigurationDao {
	private final SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationDaoImpl.class);

	@Autowired
	public ConfigurationDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * @see com.owlpad.dao.ConfigurationDao#getUserConfiguration()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Configuration getConfiguration() {
		Session session = sessionFactory.openSession();
		List<Configuration> confs = null;
		try {
			confs = (List<Configuration>) session.createQuery("from configuration").list();
		} catch (Exception e) {
			logger.info("Exception while executing getUserConfiguration", e);
		} finally {
			session.close();
		}

		if (confs != null && confs.size() > 0) {
			return confs.get(0);
		}
		return null;
	}
}
