package com.indianbank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Credential {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long userid;
	private String userPassword;

	public Credential() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Credential(Long id, Long userid, String userPassword) {
		super();
		this.id = id;
		this.userid = userid;
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "Credential [id=" + id + ", userid=" + userid + ", userPassword=" + userPassword + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
