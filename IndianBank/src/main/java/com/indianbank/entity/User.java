package com.indianbank.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "userdata")
public class User {
	@Id
	@Column(name = "userId")
	@GeneratedValue
	private Long id; // User ID

	@Column(name = "First_name")
	private String fname; // User first name

	@Column(name = "Last_name")
	private String lname; // User last name

	private char sex;
	private String email;

	@Column(name = "Account_type")
	private String acType;

//	@Column(name="Password")
	private String password; // User password

//	@Column(name="Balance")
	private double balance; // User balance

	@Column(name = "Date_of_birth")
	private String dOB; // User date of birth

	@Column(name = "Full_Address")
	private String address; // User full address

	@Column(name = "Date_of_opening")
	@Temporal(TemporalType.DATE)
	final private Date dOJ = new Date(); // User date of opening the account

	@Column(name = "Last_session")
	private String lDA; // User last session timestamp

	@Column(name = "ip_address")
	private String ipAdderess;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String fname, String lname, char sex, String email, String acType, String password,
			double balance, String dOB, String address, String ipAdderess) {
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
		this.ipAdderess = ipAdderess;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", sex=" + sex + ", email=" + email
				+ ", acType=" + acType + ", password=" + password + ", balance=" + balance + ", dOB=" + dOB
				+ ", address=" + address + ", dOJ=" + dOJ + ", lDA=" + lDA + ", Ip=" + ipAdderess + "]";
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

	public String getlDA() {
		return lDA;
	}

	public void setlDA(String lDA) {
		this.lDA = lDA;
	}

}