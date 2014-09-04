package com.owlpad.dao.impl;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.owlpad.dao.ConfigurationDao;
import com.owlpad.domain.configuration.Configuration;

public class ConfigurationDaoImpl implements ConfigurationDao{
	private SessionFactory sessionFactory;

	@Autowired
	public ConfigurationDaoImpl(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Configuration getUserConfiguration() {
		Session session = sessionFactory.openSession();		
		List<Configuration> confs = (List<Configuration>) session.createQuery("from configuration").list();
		session.close();
		
		if(confs !=null && confs.size()>0){
			return confs.get(0);
		}
		return null;
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
