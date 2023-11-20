package utils.comparison;

import java.util.Comparator;

import cms.core.Customer;

public class DateComparator implements Comparator<Customer>{

	@Override
	public int compare(Customer c1, Customer c2) {
			
		return c1.getDob().compareTo(c2.getDob());
	}
	
	
		
}
