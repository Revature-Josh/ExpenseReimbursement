package com.revature.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.revature.exceptions.*;
import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;
import com.revature.exceptions.ClientNotFoundException;

public class ExceptionController implements Controller {
	
	private Logger log = LoggerFactory.getLogger(ExceptionController.class);
	
	private ExceptionHandler<BadParamException> badParamExceptionHandler = (e, ctx) -> {
		log.warn("User entered invalid parameter. \nException message: " + e.getMessage());
		ctx.status(400);
	};
	
	private ExceptionHandler<ClientNotFoundException> clientNotFoundExceptionHandler = (e, ctx) ->{
		log.warn("Client Not Found. \nException message: " + e.getMessage());
		ctx.status(404);
	};

	private ExceptionHandler<DatabaseConnectionException> databaseConnectionExceptionHandler = (e, ctx) ->{
		log.error("Database connection failed. \nException message: " + e.getMessage());
		ctx.status(503);
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.exception(BadParamException.class, badParamExceptionHandler);
		app.exception(ClientNotFoundException.class, clientNotFoundExceptionHandler);
		app.exception(DatabaseConnectionException.class, databaseConnectionExceptionHandler);
	}

}
