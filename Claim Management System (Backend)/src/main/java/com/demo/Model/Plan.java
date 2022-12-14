package com.demo.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "plan")
public class Plan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long planId;
	
	@NotBlank
	private String planName;
	
	private double insuredAmount;
	
	private LocalDate startDate;
	
	private LocalDate endDate;

	public Plan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Plan(long planId, @NotBlank String planName, double insuredAmount, LocalDate startDate, LocalDate endDate) {
		super();
		this.planId = planId;
		this.planName = planName;
		this.insuredAmount = insuredAmount;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public long getPlanId() {
		return planId;
	}

	public void setPlanId(long planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public double getInsuredAmount() {
		return insuredAmount;
	}

	public void setInsuredAmount(double insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", planName=" + planName + ", insuredAmount=" + insuredAmount + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}

	
	

}
