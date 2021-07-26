package com.ciandt.teste.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ciandt.teste.entities.Account;
import com.ciandt.teste.json.BitcoinJson;
import com.ciandt.teste.repositories.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("transfer")
public class TransferController {
	
	@Autowired
	AccountRepository accountRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/add/{id}/{value}")
	ResponseEntity<Account> AddMoney(@PathVariable("value") double value, @PathVariable("id") long id){
		Account acc = accountRepository.findById(id).get();
		acc.setSaldo(acc.getSaldo() + value);
		accountRepository.save(acc);
		return ResponseEntity.ok(acc);
	}
	


	

}
