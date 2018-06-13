package com.fmr.customer.model;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class Customer {

	  @NotNull
	  @Size(min=3, message="SSN should have atleast 3 characters")
	private Integer ssn;
	  
	private String first_name;
	
    private String last_name;
    
    private String dob;
    
    private Gender gender;
   
    public Integer getSsn() {
		return ssn;
	}
	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
