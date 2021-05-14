package com.revature.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.app.Application;
import com.revature.dto.LoginDTO;
import com.revature.dto.MessageDTO;
import com.revature.service.LoginService;
import com.revature.model.User;

import io.javalin.Javalin;
import io.javalin.http.Handler;


public class UserController implements Controller {

	private static Logger log = LoggerFactory.getLogger(Application.class);
	private LoginService loginService;

	public UserController() {
		this.loginService = new LoginService();
	}
	
	
	private Handler loginHandler = ctx ->{
		
		LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);
		log.info("loginDTO is: " + loginDTO);
		

			User user = loginService.login(loginDTO);
			ctx.sessionAttribute("currentlyLoggedInUser" , user);
			ctx.sessionAttribute("Role", user.getuserRole());
			ctx.json(user);
			
			ctx.status(200);
		};
	
	private Handler currentUserHandler = (ctx) -> {
		User user = (User) ctx.sessionAttribute("currentlyLoggedInUser");
		
		if (user == null) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessage("User is not currently logged in!");
			ctx.json(messageDTO);
		} else {
			ctx.json(user);
		}
	};
	
	private Handler logoutHandler = (ctx) -> {
		ctx.req.getSession().invalidate();
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setMessage("User has logged out");
		ctx.json(messageDTO);
	};
	
	@Override
	public void mapEndpoints(Javalin app) {

		//login-out entry points
		app.post("/login", loginHandler);
		app.get("/userCheck", currentUserHandler);
		app.post("/logout", logoutHandler);
	}

}
