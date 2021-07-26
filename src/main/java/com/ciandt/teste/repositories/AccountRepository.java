package com.ciandt.teste.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ciandt.teste.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	@Query("SELECT a FROM Account a WHERE a.cpf = :cpf")
	Collection<Account> findByCpf(String cpf);

}
