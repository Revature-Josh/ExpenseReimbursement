package com.revature.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.app.Application;
import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.util.SessionUtility;
import com.revature.util.HashUtil;

@SuppressWarnings("deprecation")
public class UserDAO {
	
	
	private static Logger log = LoggerFactory.getLogger(Application.class);
	List<User> users = new ArrayList<User>();

	{
		Session session = SessionUtility.getSession().openSession();
		Query userQuery = session.createQuery("FROM User u");
		users = userQuery.getResultList();
	}
	
	
	public User getUserByUsernameAndPassword(LoginDTO loginDTO) {
		for (User u : users) {
			if(loginDTO.getUsername().equals(u.getUsername()) && loginDTO.getPassword().equals(u.getPassword())) {
				log.info("login data has matched a user!");
				log.info("user matched is: " + u);
				return u;
			}
		}
		return null;
	}

}
