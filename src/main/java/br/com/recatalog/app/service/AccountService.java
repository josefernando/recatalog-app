package br.com.recatalog.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.recatalog.app.security.auth.model.Account;
import br.com.recatalog.app.security.auth.repository.AccountRepository;

@Service
public class AccountService implements AccountServiceInterface {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account findById(String userLogin) {
		Optional<Account> account = 	accountRepository.findById(userLogin);
		return account.get();
	}

	@Override
	 public List<Account> findAll(){
		List<Account> itens = accountRepository.findAll();
		return itens;
	}
}