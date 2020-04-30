package br.com.recatalog.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.recatalog.app.security.auth.model.Account;
import br.com.recatalog.app.security.auth.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;

	public List<Account> listAllAccounts(){
		List<Account> itens = accountRepository.findAll();
		return itens;
	}
}
