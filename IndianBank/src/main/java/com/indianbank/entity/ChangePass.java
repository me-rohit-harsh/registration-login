package com.indianbank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ChangePass {
	@Id
	private Long id;
	
	

	private String oldPassword;
	private String newPassword;
	private String newPassword1;
	
	public ChangePass() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangePass(Long id, String oldPassword, String newPassword, String newPassword1) {
		super();
		this.id = id;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.newPassword1 = newPassword1;
	}
	@Override
	public String toString() {
		return "ChnagePass [id=" + id + ", oldPassword=" + oldPassword + ", newPassword=" + newPassword
				+ ", newPassword1=" + newPassword1 + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPassword1() {
		return newPassword1;
	}
	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}
	
}
