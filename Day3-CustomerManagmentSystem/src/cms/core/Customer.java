package cms.core;

import java.time.LocalDate;
import java.time.Period;

//Customer id(int) ,first name, last name (string),email(string),password(string),registrationAmount(double),
//dob(LocalDate),plan(ServicePlan : enum)
public class Customer implements Comparable<Customer>{
	private int custid;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private double regAmount;
	private LocalDate dob;
	private ServicePlan plan;
	private static int incCustId;
	private LocalDate lastPayDate;
	
	
	static {
		incCustId = 100;
	}

	public Customer(String firstName, String lastName, String email, String password, double regAmount, LocalDate dob,
			ServicePlan plan) {
		super();
		incCustId++;
		custid = incCustId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.regAmount = regAmount;
		this.dob = dob;
		this.plan = plan;
		this.lastPayDate = LocalDate.parse("2023-03-02");
	}
	
	public double getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}

	public ServicePlan getPlan() {
		return plan;
	}

	public void setPlan(ServicePlan plan) {
		this.plan = plan;
	}

	public LocalDate getLastPayDate() {
		return lastPayDate;
	}

	public void setLastPayDate(LocalDate lastPayDate) {
		this.lastPayDate = lastPayDate;
	}

	public Customer(String email) {
		super();
		this.email = email;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Customer) {
			Customer c = (Customer)obj;
			return this.email.equals(c.email);
		}
		return false;
	}
	@Override
	public int compareTo(Customer cust) {
		return email.compareTo(cust.email);
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [custid=" + custid + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				 + ", regAmount=" + regAmount + ", dob=" + dob + ", plan=" + plan + "]";
	}

	
	
	
}
