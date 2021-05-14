package com.revature.app;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.revature.controller.*;
import io.javalin.Javalin;
import com.revature.util.SessionUtility;
import org.hibernate.Session;

public class Application {

	private static Javalin app;
	private static Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		Session connection = SessionUtility.getSession().openSession();
		log.info("Connection to \"" + connection + "\" successful.");

		app = Javalin.create((config) -> {
			config.addStaticFiles("static");
			config.enableCorsForAllOrigins();
		});
		app.before(ctx -> {
			String URI = ctx.req.getRequestURI();
			String httpMethod = ctx.req.getMethod();
			log.info(httpMethod + " request to endpoint " + URI + " recieved.");
		});

		log.info("Mapping Controllers");
		mapControllers(new UserController(), new ExceptionController(), new ReimbursementController());
		log.info("Controllers Mapped");

		app.start(7000);

	}

	public static void mapControllers(Controller... controllers) {
		for (int i = 0; i < controllers.length; i++) {
			controllers[i].mapEndpoints(app);
		}
	}
}