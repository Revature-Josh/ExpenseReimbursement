package com.revature.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mariadb.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.app.Application;

public class SessionUtility {

	private static SessionFactory sessionFactory;
	private static Logger log = LoggerFactory.getLogger(Application.class);

	
	
	public synchronized static SessionFactory getSession() {
		Driver mariaDBDriver = new Driver();
		try {
			DriverManager.registerDriver(mariaDBDriver);
		} catch (SQLException e) {
			log.info("SQLException with message:" + e.getMessage());
		}
		
		
		if (sessionFactory == null) {
			sessionFactory = new Configuration()
								.setProperty("hibernate.connection.username", System.getenv("DB_USERNAME"))
								.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"))
								.configure("hibernate.cfg.xml")
								.buildSessionFactory();
		}
		
		return sessionFactory;
	}
	
}
