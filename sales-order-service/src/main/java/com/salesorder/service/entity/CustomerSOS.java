package com.salesorder.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Customer_SOS")
public class CustomerSOS {
	
	@Id
	@Column(name="cust_id")
	private int id;
	
	@Column(name="cust_first_name")
	private String firstname;
	
	@Column(name="cust_last_name")
	private String lastname;
	
	@Column(name="cust_email")
	private String email;
	
	protected CustomerSOS() {		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLasttname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
