package com.cubic.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerSearchResult {
	private List<CustomerVO> customers;

	public List<CustomerVO> getCustomers() {
		if(customers == null) {
			customers = new ArrayList<CustomerVO>();
		}
		return customers;
	}

	public void setCustomers(List<CustomerVO> customers) {
		this.customers = customers;
	}
	
}
