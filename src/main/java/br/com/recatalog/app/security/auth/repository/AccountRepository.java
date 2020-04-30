package br.com.recatalog.app.security.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.recatalog.app.security.auth.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
	
}