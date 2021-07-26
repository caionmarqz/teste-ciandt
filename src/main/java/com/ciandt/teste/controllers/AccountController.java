package com.ciandt.teste.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ciandt.teste.entities.Account;
import com.ciandt.teste.repositories.AccountRepository;

@RestController
@RequestMapping("account")
public class AccountController {

	@Autowired
	AccountRepository accountRepository;
	
	@RequestMapping(method = RequestMethod.POST, value = "/new", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> newAccount(@RequestBody Account acc) {
	if (accountRepository.findByCpf(acc.getCpf()).size() == 0)
	{
	accountRepository.save(acc);
	return ResponseEntity.ok("");
	}
	return ResponseEntity.internalServerError().build();
	}
}
