package br.com.recatalog.app.security.auth.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountRepositoryTest {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Test
	public void contextLoads() {
	}
	
    @Test
    void injectedComponentsAreNotNull() {
    	assertNotNull(accountRepository);
    }
}