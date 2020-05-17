package com.demo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entities.Billing;
import com.demo.repositories.BillingRepository;

@Service
@Transactional
public class BillingService {

	@Autowired
	private BillingRepository repo;
	
	public List<Billing> listAll() {
		return repo.findAll();
	}
	
	public void save(Billing billing) {
		repo.save(billing);
	}
	
	public void delete(Long code) {
		repo.deleteById(code);
	}
	
	public Billing get(Long code) {
		return repo.findById(code).get();
	}
	
	public float calculate()
	{
		float total=0;
		List<Billing> billing=repo.findAll();
		for(Billing b:billing)
		{
			total+=(b.getBquantity()*(b.getBprice()+((b.getBgst()*b.getBprice())/100)));
		}
		return(total);
	}
	
	public Billing findCode(Long code) {
		return repo.findByCode(code);
	}
}
