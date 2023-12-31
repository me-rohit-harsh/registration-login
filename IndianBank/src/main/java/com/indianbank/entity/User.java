package com.indianbank.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "bankusers")
public class User {
	@Id
	@Column(name = "userId")
	@GeneratedValue
	private Long id;

	@Column(name = "First_name")
	private String fname;

	@Column(name = "Last_name")
	private String lname;

	private char sex;
	private String email;

	@Column(name = "Account_type")
	private String acType;

//	@Column(name="Password")
	private String password;

//	@Column(name="Balance")
	private double balance;

	@Column(name = "Date_of_birth")
	private String dOB;

	@Column(name = "Full_Address")
	private String address;

	@Column(name = "Date_of_opening")
	@Temporal(TemporalType.DATE)
	final private Date dOJ = new Date();

	@Column(name = "Last_session")
	private String lDA;

	@Column(name = "ip_address")
	private String ipAdderess;

	@OneToOne
	private DelAdd delAdd;
	
	@OneToOne
//	@Transient
	private Transactions transaction;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", sex=" + sex + ", email=" + email
				+ ", acType=" + acType + ", password=" + password + ", balance=" + balance + ", dOB=" + dOB
				+ ", address=" + address + ", dOJ=" + dOJ + ", lDA=" + lDA + ", ipAdderess=" + ipAdderess + ", delAdd="
				+ delAdd + ", transaction=" + transaction + "]";
	}

	

	public User(Long id, String fname, String lname, char sex, String email, String acType, String password,
			double balance, String dOB, String address, String lDA, String ipAdderess, DelAdd delAdd,
			Transactions transaction) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.sex = sex;
		this.email = email;
		this.acType = acType;
		this.password = password;
		this.balance = balance;
		this.dOB = dOB;
		this.address = address;
		this.lDA = lDA;
		this.ipAdderess = ipAdderess;
		this.delAdd = delAdd;
		this.transaction = transaction;
	}

	public Transactions getTransaction() {
		return transaction;
	}

	public void setTransaction(Transactions transaction) {
		this.transaction = transaction;
	}

	public String getIpAdderess() {
		return ipAdderess;
	}

	public void setIpAdderess(String ipAdderess) {
		this.ipAdderess = ipAdderess;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAcType() {
		return acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getdOB() {
		return dOB;
	}

	public void setdOB(String dOB) {
		this.dOB = dOB;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getdOJ() {
		return dOJ;
	}

	public DelAdd getDelAdd() {
		return delAdd;
	}

	public void setDelAdd(DelAdd delAdd) {
		this.delAdd = delAdd;
	}

	public String getlDA() {
		return lDA;
	}

	public void setlDA(String lDA) {
		this.lDA = lDA;
	}

}