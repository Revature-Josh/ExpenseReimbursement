package com.revature.service;

import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.exceptions.BadParamException;
import com.revature.exceptions.LoginException;
import com.revature.dao.UserDAO;

public class LoginService {

	private UserDAO userDAO;
	
	
	public LoginService() {
		this.userDAO = new UserDAO();
	}
	
	public LoginService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	public User login(LoginDTO loginDTO) throws BadParamException, LoginException  {
		//check for blank fields
		if (loginDTO.getUsername().trim().equals("") || loginDTO.getPassword().trim().equals("")) {
			throw new BadParamException("username and password fields can not be blanck");
		}
		
		//get user
		User user = userDAO.getUserByUsernameAndPassword(loginDTO);
		
		//check user was obtained
		if (user == null) {
			throw new LoginException("User was not able to login with supplied username and password");
		}
		
		return user;
	}
	
}
