package com.ciandt.teste.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.client.RestTemplate;

import com.ciandt.teste.json.BitcoinJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Entity
@Table(name = "contas")

public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cpf;
	private double saldo;
	private float btc;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public float getBtc() {
		return btc;
	}
	public void setBtc(float btc) {
		this.btc = btc;
	}
	public boolean addBtc(float btc) throws JsonMappingException, JsonProcessingException {
		float currency = getBitcoinCurrency();
		if ((this.saldo / currency) >= btc) {
		this.saldo -= btc*currency;
		this.btc += btc;
		return true;
		}
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	private float getBitcoinCurrency() throws JsonMappingException, JsonProcessingException
	{
	    final String uri = "https://api.coinbase.com/v2/prices/spot?currency=BRL";
	    
	    RestTemplate rest = new RestTemplate();
	    BitcoinJson bcj = rest.getForObject(uri, BitcoinJson.class);
	    return bcj.getData().getAmount();
	}
	
	
	
}
