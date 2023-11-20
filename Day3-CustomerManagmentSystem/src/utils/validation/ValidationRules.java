package utils.validation;

import java.time.LocalDate;
import java.util.List;

import cms.core.Customer;
import cms.core.ServicePlan;
import cms.exception.InvalidInputException;

public class ValidationRules {

	public static ServicePlan parseAndValidatePlan(String plan) throws InvalidInputException  {
		try {
		return ServicePlan.valueOf(plan.toUpperCase());
		}catch(IllegalArgumentException e){
			throw new InvalidInputException("You have entered Plan that is not in our Service plans");
		}
	}
	
	public static void validateAmount(ServicePlan plan,double amount) throws InvalidInputException{
		if(plan.getPlanAmount() != amount) {
			throw new InvalidInputException("Registration amount does not match with plan");
		}
	
	}
	
	public static void validateEmail(String email) throws InvalidInputException{
		String regEx = "[a-z]+[a-z0-9]+@[a-z]*\\.(com|org|net)";
		if(!email.matches(regEx)) {
			throw new InvalidInputException("Email Invalid");
		
		
		}	
	}
	
	public static void checkDupEmail(String email,List<Customer> customer) throws InvalidInputException{
		Customer c = new Customer(email);
		if(customer.contains(c))
			throw new InvalidInputException("Email address should be unique");
			
	}
	
	public static LocalDate parseDate(String date) {
		return LocalDate.parse(date);
	}
	
	//method to call all validation rules in one methods
	
	public static Customer validateAllInputs(String firstName, String lastName, String email, String password, double regAmount, String dob,
			String plan, List<Customer> customer) throws InvalidInputException{
		ServicePlan validatePlan = parseAndValidatePlan(plan.toUpperCase());
		validateAmount(validatePlan, regAmount);
		validateEmail(email);
		checkDupEmail(email, customer);
		
		return new Customer(firstName, lastName, email, password, regAmount, parseDate(dob), ServicePlan.valueOf(plan));
	}
	
}
