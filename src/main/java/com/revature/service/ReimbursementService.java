package com.revature.service;

import java.util.ArrayList;


import com.revature.dao.ReimbursementDAO;
import com.revature.dto.ReimbursementDTO;
import com.revature.exceptions.BadParamException;
import com.revature.model.Reimbursement;

public class ReimbursementService {
	ReimbursementDAO reimbDAO = new ReimbursementDAO();
	
	public Reimbursement addReimbursement(ReimbursementDTO reimbursementdto) {
		return reimbDAO.addReimbursement(reimbursementdto);
	}
	
	public ArrayList<Reimbursement> getReimbursementsByAuthorID(int id) throws BadParamException {
		if (id <= 0) {
			throw new BadParamException("required fields were incorrectly submitted");
		}
		return reimbDAO.getReimbursementsByAuthorID(id);		
	}

	public ArrayList<Reimbursement> getAllReimbursements() {
		return reimbDAO.getAllReimbursements();
	}

	public Reimbursement resolveReimbursement(ReimbursementDTO reimbursementdto) throws Exception {
		if (reimbursementdto.getId() <= 0) {
			throw new BadParamException("invalid id");
		}		
		return reimbDAO.resolveReimbursement(reimbursementdto);
	}

	public ArrayList<Reimbursement> getReimbursementsByStatus(int status) throws BadParamException {
		if (status <= 0) {
			throw new BadParamException("new status was incorrectly submitted");
		}
		return reimbDAO.getReimbursementsByStatus(status);
	}
	
}
