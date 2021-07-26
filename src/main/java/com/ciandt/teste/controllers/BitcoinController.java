package com.ciandt.teste.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ciandt.teste.entities.Account;
import com.ciandt.teste.repositories.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("bitcoin")
public class BitcoinController {

	@Autowired
	AccountRepository accountRepository;

	@RequestMapping(value = "/buy/{id}/{value}", method = RequestMethod.GET)
	public ResponseEntity<Account> bitcoinBuy(@PathVariable("id") Long id, @PathVariable("value") float value) throws JsonMappingException, JsonProcessingException {
		Account acc = accountRepository.findById(id).get();
		if (acc.addBtc(value)) {
			accountRepository.save(acc);
			return ResponseEntity.ok(acc);
		}
		else
		{
			return ResponseEntity.ok(null);
		}
	}
}
