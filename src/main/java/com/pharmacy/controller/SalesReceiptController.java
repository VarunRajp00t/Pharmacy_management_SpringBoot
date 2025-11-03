package com.pharmacy.controller;

import com.pharmacy.model.Customer;
import com.pharmacy.model.SalesReceipt;
import com.pharmacy.service.CustomerService;
import com.pharmacy.service.SalesReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "http://localhost:8080")
public class SalesReceiptController {

	@Autowired
	private SalesReceiptService salesReceiptService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/all")
	public List<SalesReceipt> getAllReceipts() {
		return salesReceiptService.getAllReceipts();
	}

	@PostMapping("/add")
	public SalesReceipt addReceipt(@RequestParam Long customerId, @RequestParam double totalAmount,
			@RequestParam String paymentMethod) {
		Customer customer = customerService.getCustomerById(customerId);
		if (customer == null) {
			throw new RuntimeException("Customer not found with ID: " + customerId);
		}

		SalesReceipt receipt = new SalesReceipt();
		receipt.setCustomer(customer);
		receipt.setTotalAmount(totalAmount);
		receipt.setPaymentMethod(paymentMethod);
		receipt.setPurchaseDate(LocalDateTime.now());

		return salesReceiptService.saveReceipt(receipt);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteReceipt(@PathVariable Long id) {
		salesReceiptService.deleteReceipt(id);
		return "Sales receipt deleted successfully!";
	}
}
