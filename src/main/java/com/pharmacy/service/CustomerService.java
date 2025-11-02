package com.pharmacy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.Customer;
import com.pharmacy.repository.CartRepository;
import com.pharmacy.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CartRepository cartRepository;

	// 1. Add Customer
	public Customer addCustomer(Customer customer) {
		if (customerRepository.existsByEmail(customer.getEmail())) {
			throw new IllegalArgumentException("Email already exists: " + customer.getEmail());
		}
		return customerRepository.save(customer);
	}

	// 2. Update Customer
	public Customer updateCustomer(Long id, Customer updatedCustomer) {
		Optional<Customer> existingCustomer = customerRepository.findById(id);
		if (existingCustomer.isPresent()) {
			Customer customer = existingCustomer.get();
			customer.setName(updatedCustomer.getName());
			customer.setEmail(updatedCustomer.getEmail());
			customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
			customer.setAddress(updatedCustomer.getAddress());
			return customerRepository.save(customer);
		}
		return null;
	}

	// 3. Delete Customer

	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

	// 4. View All Customers
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	// View customer
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
	}

	// 5. Search by Email
	public Customer getCustomerByEmail(String email) {
		return customerRepository.findByEmail(email);
	}
}
