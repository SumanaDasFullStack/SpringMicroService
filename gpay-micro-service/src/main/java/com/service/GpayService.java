package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.entity.Gpay;
import com.repository.GpayRepository;

@Service
public class GpayService {

	@Autowired
	GpayRepository gpayRepository;
	
	@Autowired
	RestTemplate restTemplate;	// like axios or HttpClient in Java 
	
	public String createAccount(Gpay gpay) {
		gpayRepository.save(gpay);
		return "Account Created";
	}
	
	
	public String findBalance(int gpayid) {
		Optional<Gpay> result = gpayRepository.findById(gpayid);
		if(result.isPresent()) {
			Gpay gpay  = result.get();
			
		//	String data = restTemplate.getForObject("http://localhost:9393/account/findBalanceByEmailId/"+gpay.getEmailid(), String.class);
			String data = restTemplate.getForObject("http://ACCOUNTSERVICE/account/findBalanceByEmailId/"+gpay.getEmailid(), String.class);
			
			return data;
			
		}else {
			return "gpay account not exists";
		}
	}
}