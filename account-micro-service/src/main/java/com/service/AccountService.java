package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.AccountEntity;
import com.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	public String createAccount(AccountEntity account) {
		if(accountRepository.existsById(account.getAccno())) {
			return "Account number must be unique";
		}else {
			accountRepository.save(account);
			return "Account created successfully";
		}
	}
	
	public String findBalance(int accno) {
		Optional<AccountEntity> result = accountRepository.findById(accno);
		if(result.isPresent()) {
			AccountEntity acc = result.get();
			return "Your balance is "+acc.getAmount();
		}else {
			return "Account not exists";
		}
	}
	
	public String withdraw(AccountEntity account) {		// accno and amount 
		Optional<AccountEntity> result = accountRepository.findById(account.getAccno());
		if(result.isPresent()) {
			AccountEntity acc = result.get();
			if(acc.getAmount()>=account.getAmount()) {
				acc.setAmount(acc.getAmount()-account.getAmount());
				accountRepository.saveAndFlush(acc);
				return "Withdraw amount successfully";
			}else {
				return "Can't withdrawn no amount present";
			}
		}else {
			return "Account not exists";
		}
	}
	
	public String deposit(AccountEntity account) {		// accno and amount 
		Optional<AccountEntity> result = accountRepository.findById(account.getAccno());
		if(result.isPresent()) {
			AccountEntity acc = result.get();
			acc.setAmount(acc.getAmount()+account.getAmount());
			accountRepository.saveAndFlush(acc);
			return "Deposit amount successfully";
		}else {
			return "Account not exists";
		}
	}
	
	public String findBalanceUsingEmailId(String emailid) {
		try {
			return "Your balance is "+accountRepository.findBalanceByEmailId(emailid);
		} catch (Exception e) {
			return "Account not exists with emailid as "+emailid;
		}
	}
}