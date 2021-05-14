package com.revature.model;

import java.sql.Timestamp;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

import com.revature.dto.ReimbursementDTO;

@Entity
@Table(name = "ERS_REIMBURSEMENT")
public class Reimbursement {
	
	public Reimbursement(ReimbursementDTO reimbDTO) {
			this.authorID = reimbDTO.getAuthorID();
			this.reimbAmount = reimbDTO.getReimbAmount();
			this.reimbDescription = reimbDTO.getReimbDescription();
			this.ReimbReciept = reimbDTO.getReimbReciept();
			this.reimbSubmitted = reimbDTO.getReimbSubmitted();
			this.typeID = reimbDTO.getTypeID();
			
			if(reimbDTO.getReimbResolved()!=null) { 
				this.reimbResolved = reimbDTO.getReimbResolved();
				this.resolverID = reimbDTO.getResolverID();
				this.statusID = reimbDTO.getStatusID();
			} else {
				this.statusID = 1;
			}
			
	}
	
	public Reimbursement() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REIMB_ID")
	int id;
	
	@Column(name = "REIMB_AMOUNT", precision = 10, scale=2)
	double reimbAmount;
	
	@Column(name = "REIMB_SUBMITTED")
	Timestamp reimbSubmitted;
	
	@Column(name = "REIMB_RESOLVED")
	Timestamp reimbResolved;
	
	@Column(name = "REIMB_DESCRIPTION", length = 250)
	String reimbDescription;
	
	@Column(name = "REIMB_RECIEPT")
	Blob ReimbReciept;
	
	@Column(name = "REIMB_AUTHOR")
	int authorID;
	
	@Column(name = "REIMB_RESOLVER")
	Integer resolverID;
	
	// with current dummy data; 1 = pending; 2 = approved; 3 = denied;
	@Column(name = "REIMB_STATUS_ID")
	int statusID;
	
	// with current dummy data; 1 = lodging; 2 = travel; 3 = food; 4 = other;
	@Column(name = "REIMB_TYPE_ID")
	int typeID;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ReimbReciept == null) ? 0 : ReimbReciept.hashCode());
		result = prime * result + authorID;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(reimbAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + ((reimbResolved == null) ? 0 : reimbResolved.hashCode());
		result = prime * result + ((reimbSubmitted == null) ? 0 : reimbSubmitted.hashCode());
		result = prime * result + resolverID;
		result = prime * result + statusID;
		result = prime * result + typeID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (ReimbReciept == null) {
			if (other.ReimbReciept != null)
				return false;
		}
		if (authorID != other.authorID)
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(reimbAmount) != Double.doubleToLongBits(other.reimbAmount))
			return false;
		if (reimbDescription == null) {
			if (other.reimbDescription != null)
				return false;
		} else if (!reimbDescription.equals(other.reimbDescription))
			return false;
		if (reimbResolved == null) {
			if (other.reimbResolved != null)
				return false;
		} else if (!reimbResolved.equals(other.reimbResolved))
			return false;
		if (reimbSubmitted == null) {
			if (other.reimbSubmitted != null)
				return false;
		} else if (!reimbSubmitted.equals(other.reimbSubmitted))
			return false;
		if (resolverID != other.resolverID)
			return false;
		if (statusID != other.statusID)
			return false;
		if (typeID != other.typeID)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public Timestamp getReimbSubmitted() {
		return reimbSubmitted;
	}

	public void setReimbSubmitted(Timestamp reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}

	public Timestamp getReimbResolved() {
		return reimbResolved;
	}

	public void setReimbResolved(Timestamp reimbResolved) {
		this.reimbResolved = reimbResolved;
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public Blob getReimbReciept() {
		return ReimbReciept;
	}

	public void setReimbReciept(Blob reimbReciept) {
		ReimbReciept = reimbReciept;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public Integer getResolverID() {
		return resolverID;
	}

	public void setResolverID(int resolverID) {
		this.resolverID = resolverID;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
}
