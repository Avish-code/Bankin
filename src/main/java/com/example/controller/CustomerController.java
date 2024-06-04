package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.model.Customer;
import com.example.repo.BankRepo;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/bank")
public class CustomerController {
	@Autowired 
	BankRepo br;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	RestTemplate restTemplate;

	/*
	 * @GetMapping("/read") public String method1() { LOG.info("Inside method1");
	 * String baseUrl = "http://localhost:8889/read"; String response = (String)
	 * restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class).getBody();
	 * LOG.info("The response received by method1 is " + response); return response;
	 * }
	 */

	  @GetMapping("/read") 
	  public List<Customer>read() 
	  {
		  return br.findAll(); 
	  }
	 @PutMapping("/withdraw/{id}/{amount}")
	 public void updatebalance(@PathVariable long id,@PathVariable double amount)
	 {
		 Optional<Customer> cst= br.findById(id);
		double currentamount= cst.get().getBalance();
		double newamt=0;
		if(currentamount>=0)
		{
			newamt=newamt+(currentamount-amount);
			cst.get().setBalance(newamt);
		}
		
	 }
	 @PutMapping("/deposit/{id}/{amount}")
	 public void updatebalance1(@PathVariable long id,@PathVariable double amount)
	 {
		 Optional<Customer> cst= br.findById(id);
		double currentamount= cst.get().getBalance();
		double newamt=0;
		if(currentamount>=0)
		{
			newamt=newamt+(currentamount+amount);
			cst.get().setBalance(newamt);
		}
		
	 }
	
	@PostMapping("/add")
	public void add(@RequestBody Customer ct)
	{
		Customer newct=new Customer();
		newct.setName(ct.getName());
		newct.setUserName(ct.getUserName());
		newct.setPassword(ct.getPassword());
		newct.setAge(ct.getAge());
		newct.setAccNum(ct.getAccNum());
		newct.setEmail(ct.getEmail());
		newct.setAddress(ct.getAddress());
		newct.setBalance(ct.getBalance());
		newct.setPhone(ct.getPhone());
		
		br.save(newct);
	}
	@PutMapping("/update/{id}")
    public Customer update(@PathVariable long id, @RequestBody Customer e) {
        Optional<Customer> existingCustomer = br.findById(id);
 
        if (existingCustomer.isPresent()) {
            Customer customerToUpdate = existingCustomer.get();
            customerToUpdate.setName(e.getName());
            customerToUpdate.setBalance(e.getBalance());
            customerToUpdate.setEmail(e.getEmail());
            customerToUpdate.setAddress(e.getAddress());
            customerToUpdate.setAccNum(e.getAccNum());
            customerToUpdate.setPhone(e.getPhone());
            customerToUpdate.setUserName(e.getUserName());
            customerToUpdate.setPassword(e.getPassword());
            return br.save(customerToUpdate);
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }
	@PutMapping("/update/{id}")
	public  void update(@RequestBody Customer cust,@PathVariable long id)
	{
		
		Optional<Customer> custold=br.findById(id);
		 custold.get().setName(cust.getName());
		 custold.get().setUserName(cust.getUserName());
 		 custold.get().setPassword(cust.getPassword());
		 custold.get().setAge(cust.getAge());
		 custold.get().setAccNum(cust.getAccNum());
		 custold.get().setAddress(cust.getAddress());
		 custold.get().setEmail(cust.getEmail());
		 custold.get().setPhone(cust.getPhone());
		 custold.get().setBalance(cust.getBalance());
		 
		 br.save(custold.get());
	}
	
	
	@GetMapping("/readbal/{id}")
	public double readbal(@PathVariable long id)
	{
		Optional<Customer> bal=br.findById(id);
		double balance=0;
		if(bal!=null) {
			balance=bal.get().getBalance();
		}else {
			balance=1120;
		}
		return balance;
	}
	
	@GetMapping("/readprofile/{id}")
	public Optional<Customer> readprofile(@PathVariable long id)
	{
		Optional<Customer> cust= br.findById(id);
		return cust;
		
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable long id)
	{
		br.deleteById(id);
	}
	
		

	

}
