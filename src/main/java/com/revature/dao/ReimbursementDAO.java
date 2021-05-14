package com.revature.dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.app.Application;
import com.revature.dto.ReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.util.SessionUtility;

public class ReimbursementDAO {

	private static Logger log = LoggerFactory.getLogger(Application.class);
	ArrayList<Reimbursement> reimbs = getAllReimbursements();

	public ArrayList<Reimbursement> getReimbursementsByAuthorID(int id) {

		ArrayList<Reimbursement> reimbByAuthor = new ArrayList<Reimbursement>();
		
		for (Reimbursement r : reimbs) {
			if(id == r.getAuthorID()) {
				reimbByAuthor.add(r);				
			}
		}
		return reimbByAuthor;
	}	
	
	public ArrayList<Reimbursement> getAllReimbursements(){
			Session session = SessionUtility.getSession().openSession();
			Query userQuery = session.createQuery("FROM Reimbursement r");
			return (ArrayList<Reimbursement>)userQuery.getResultList();
	}
	
	public Reimbursement addReimbursement(ReimbursementDTO reimbursementdto) {
		Session session = SessionUtility.getSession().openSession();
		Transaction tx = session.beginTransaction();
		
		Reimbursement reimb = new Reimbursement(reimbursementdto);
		session.persist(reimb);
		tx.commit();
		
		return reimb;
	}

	public Reimbursement resolveReimbursement(ReimbursementDTO reimbursementdto) throws Exception {
		
		Session session = SessionUtility.getSession().openSession();
		Transaction tx = session.beginTransaction();

		Reimbursement resolvedReimb = session.get(Reimbursement.class, reimbursementdto.getId());
		if (resolvedReimb == null) {
			throw new Exception("The reimbursement meant to be resolved does not exist");
		}
		
		resolvedReimb.setResolverID(reimbursementdto.getResolverID());
		resolvedReimb.setStatusID(reimbursementdto.getStatusID());
		resolvedReimb.setReimbResolved(new Timestamp(System.currentTimeMillis()));
		
		tx.commit();
		
		return resolvedReimb;
	}

	public ArrayList<Reimbursement> getReimbursementsByStatus(int status) {
		
		Session session = SessionUtility.getSession().openSession();
		ArrayList<Reimbursement> rs = new ArrayList<Reimbursement>();
		for(Reimbursement r: reimbs) {
			if (r.getStatusID() == status) {
				rs.add(r);
			}
		}
		return rs;
	}
	
}
