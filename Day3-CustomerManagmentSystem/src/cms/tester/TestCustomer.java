package cms.tester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import cms.core.Customer;
import cms.core.ServicePlan;
import utils.comparison.DateComparator;

import static utils.customers.CustomerUtils.*;
import static utils.validation.ValidationRules.*;

public class TestCustomer {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {

			List<Customer> custList = new ArrayList<>();

			boolean exit = false;

			while (!exit) {
				System.out.println(" 1. Sign up\n 2. Sign in\n 3. Change password\n 4. Un subscribe customer\n"
						+ " 5. Display all customers.\n 6. Sort Customer by email\n 7. Sort Customer by Date of birth\n"
						+ " 8. Select plan and Pay subscription amount\n"
						+ " 9. display complete names of the customers , who have not paid the subscription, for last 3 months\n"
						+ " 10. remove all those customer details whose subscription is pending for last 6 months\n0. Exit");

				System.out.println("Enter choice:");
				try {
					switch (sc.nextInt()) {
					case 0:
						exit = false;
						break;
					case 1:
						System.out
								.println("Enter customer details: firstName, lastName, email, password, regAmount, dob "
										+ "Select plan");
						custList.addAll(addData());
						Customer customer = validateAllInputs(sc.next(), sc.next(), sc.next(), sc.next(),
								sc.nextDouble(), sc.next(), sc.next(), custList);
						// adding customer:

						custList.add(customer);
						System.out.println("Customer registered successfully");
						break;
					case 2:
						System.out.println("Enter email and password:");
						customer = authenticate(sc.next(), sc.next(), custList);
						System.out.println("Login Successful");
						break;
					case 3:
						System.out.println("Enter email and old password:");
						System.out.println("Enter email and password:");
						customer = authenticate(sc.next(), sc.next(), custList);
							System.out.println("Enter new password:");
							customer.setPassword(sc.next());
							System.out.println("password changed successfully");
						break;
					case 4:
						System.out.println("Enter email address:");
						customer = new Customer(sc.next());
						custList.remove(customer);
						break;

					case 5:
						for (Customer cust : custList) {
							System.out.println(cust);
						}
					case 6:
						Collections.sort(custList);
						break;

					case 7:
						Collections.sort(custList, new DateComparator());
						break;
					case 8:
						System.out.println("Enter email and password:");
						customer = authenticate(sc.next(), sc.next(), custList);
							for (ServicePlan plan : ServicePlan.values()) {
								System.out.println(plan);
							}
							System.out.println("Select one plan and enter amount to pay:");
							rechargePlan(customer,sc.next().toUpperCase(),sc.nextDouble());
							System.out.println("Payment Successfull !! your plan is added");
						break;
					case 9:
						List<Customer> dues = overdueList(custList);

						for (Customer cust : dues) {
							System.out.println(cust);
						}
						break;
					case 10:
						removeOverDueCustomers(custList);
						break;

					}
				} catch (Exception e) {
					sc.nextLine();
					System.out.println(e.getMessage());
				}
			}
		}

	}

}
