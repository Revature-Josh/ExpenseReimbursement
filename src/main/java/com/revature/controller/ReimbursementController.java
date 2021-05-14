package com.revature.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.app.Application;
import com.revature.dto.ReimbursementDTO;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController implements Controller {
	
	private static Logger log = LoggerFactory.getLogger(Application.class);
	private ReimbursementService serv = new ReimbursementService();

	//Employee Services
	private Handler addReimbursement = ctx ->{
		ReimbursementDTO reimb = ctx.bodyAsClass(ReimbursementDTO.class);
		log.info("Adding new Reimbursement with values: " + reimb);
		serv.addReimbursement(reimb);
		ctx.status(200);
	};
	private Handler getReimbursementByAuthorId = ctx ->{
		log.info("getting reimbursement with id: ", ctx.pathParam("id"));
		ctx.json(serv.getReimbursementsByAuthorID(Integer.parseInt(ctx.pathParam("id"))));
		ctx.status(200);
	};
	
	//Finance Manager Services
	private Handler getAllReimbursements = ctx ->{
		log.info("getting all reimbursements");
		ctx.json(serv.getAllReimbursements());
		ctx.status(200);
	};
	private Handler resolveReimbursement = ctx ->{
		//int reimbID = Integer.parseInt(ctx.pathParam("id"));
		ReimbursementDTO reimb = ctx.bodyAsClass(ReimbursementDTO.class);
		log.info("The selected reimbursement will be set to " + reimb);
		ctx.json(serv.resolveReimbursement(reimb));
		ctx.status(200);
	};
	private Handler getReimbursementByStatus = ctx ->{
		log.info("Getting Reimbursements with StatusID: " + ctx.pathParam("statusID"));
		int status = Integer.parseInt(ctx.pathParam("statusID"));
		ctx.json(serv.getReimbursementsByStatus(status));
		ctx.status(200);
	};

	
	@Override
	public void mapEndpoints(Javalin app) {
		

		
		//Employee endpoints
		app.post("/addReimb", addReimbursement);	
		app.get("/author/reimb/:id", getReimbursementByAuthorId);

		//Finance Manager endpoints
		app.get("/reimb", getAllReimbursements);
		app.put("/resolve", resolveReimbursement);
		app.get("/getreimb/:statusID", getReimbursementByStatus);

	}
}
