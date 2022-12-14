package com.demo.Model;

import java.time.LocalDate;
import com.demo.Model.Plan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Table(name="member")
@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long memberId;
	
	@NotBlank
	private String memberName;
	
	private LocalDate dob;
	
	@NotBlank
	private String emailId;
	
	private String contactNo;
	
	private String city;
	
	private String state;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;

	@OneToOne
	@JoinColumn(name = "plan_id", referencedColumnName = "planId" )
	private Plan plan;

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(long memberId, @NotBlank String memberName, LocalDate dob, @NotBlank String emailId, String contactNo,
			String city, String state, @NotBlank String username, @NotBlank String password, Plan plan) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.dob = dob;
		this.emailId = emailId;
		this.contactNo = contactNo;
		this.city = city;
		this.state = state;
		this.username = username;
		this.password = password;
		this.plan = plan;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", dob=" + dob + ", emailId=" + emailId
				+ ", contactNo=" + contactNo + ", city=" + city + ", state=" + state + ", username=" + username
				+ ", password=" + password + ", plan=" + plan + "]";
	}

	
	
	
}