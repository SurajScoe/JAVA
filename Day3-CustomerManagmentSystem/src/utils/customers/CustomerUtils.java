package utils.customers;

import static utils.validation.ValidationRules.validateAllInputs;
import static utils.validation.ValidationRules.validateAmount;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cms.core.Customer;
import cms.core.ServicePlan;
import cms.exception.InvalidInputException;

public class CustomerUtils {
	
		public static Customer checkEmail(String email,List<Customer> cList)throws InvalidInputException{
			
			/*Call the Constructor and with PK as para*/
			Customer c = new Customer(email);
			/*if the object with specified element the it returns 1st ocurrence in
			 *the list if no then -1 */
			int index = cList.indexOf(c);
			
			if(index == -1)
				throw new InvalidInputException("no such user, Check your Email Address");
			
			return cList.get(index);
		}
		
		public static Customer authenticate(String email,String password,List<Customer>custList) throws InvalidInputException {
			Customer c = checkEmail(email, custList);
			
			if(!password.equals(c.getPassword()))
				throw new InvalidInputException("Invalid Password");
			
			
			return c;
		}
		
		
		
		public static List<Customer> addData() throws InvalidInputException{
			
			List<Customer> custData = new ArrayList<>();
			
			custData.add(validateAllInputs("Priya", "Patel", "priya@gmail.com", "priya123", 5000, "1998-03-05", "DIAMOND", custData));
			custData.add(validateAllInputs("Rajesh", "Kumar", "rajesh@gmail.com", "rajesh123", 1000, "1994-04-05", "SILVER", custData));
			custData.add(validateAllInputs("Aisha", "Gupta", "aisha@gmail.com", "aisha123", 2000, "2001-06-05", "GOLD", custData));
			custData.add(validateAllInputs("Arjun", "Sharma", "arjun@gmail.com", "arjun123", 10000, "2000-07-05", "PLATINUM", custData));
			custData.add(validateAllInputs("Meera", "Desai", "meera@gmail.com", "meera123", 5000, "1999-03-05", "DIAMOND", custData));
			custData.add(validateAllInputs("Rohit", "Deshmukh", "rohit@gmail.com", "rohit123", 1000, "1997-08-05", "SILVER", custData));
			custData.add(validateAllInputs("Kavita", "Joshi", "kavita@gmail.com", "kavita123", 10000, "2003-09-05", "PLATINUM", custData));
			
			return custData;
		}
		
		public static List<Customer> overdueList(List<Customer> cList){
			List<Customer> dueList = new ArrayList<>();
			for(Customer customer : cList) {
				LocalDate curDate = LocalDate.now();
				Period period = Period.between(customer.getLastPayDate(), curDate);
				
				long months = period.toTotalMonths();
				
				if(months>3) {
					dueList.add(customer);
				}
			}
			return dueList;
		}
		
		public static void removeOverDueCustomers(List<Customer> cList) {
//			List<Customer> removedCust = new ArrayList<>();
//			for(Customer customer : cList) {
//				LocalDate curDate = LocalDate.now();
//				Period period = Period.between(customer.getLastPayDate(), curDate);
//				
//				long months = period.toTotalMonths();
//				
//				if(months>6) {
//					removedCust.add(customer);
//				}
//			}
			
			Iterator<Customer> itr = cList.iterator();
			
			while(itr.hasNext()) {
				LocalDate curDate = LocalDate.now();
				Period period = Period.between(itr.next().getLastPayDate(), curDate);
				
				long months = period.toTotalMonths();
				
				if(months>6) {
					itr.remove();
				}
				
			}
			

		}
		
		public static void rechargePlan(Customer customer,String plan,double amt) throws InvalidInputException{
			
			ServicePlan selectedPlan = ServicePlan.valueOf(plan.toUpperCase());
			validateAmount(selectedPlan, amt);
			customer.setPlan(selectedPlan);
			customer.setRegAmount(amt);
			customer.setLastPayDate(LocalDate.now());
			}
		}
		






//double regAmt;
//regAmt = sc.nextDouble();
//ServicePlan selectedPlan = ServicePlan.valueOf(sc.next().toUpperCase());
//validateAmount(selectedPlan, regAmt);
//customer.setPlan(selectedPlan);
//customer.setRegAmount(regAmt);
//customer.setLastPayDate(LocalDate.now());
