package br.com.recatalog.app.security.auth.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.recatalog.app.security.auth.model.Permission;

@SpringBootTest
public class PermissionRepositoryTest {
	
	@Autowired
	PermissionRepository permissionRepository;
	
	final static String auth = "USERx";
	
	@Test
	public void contextLoads() {
	}
	
    @Test
    void injectedComponentsAreNotNull() {
    	assertNotNull(permissionRepository);
    }
    
    @Test
    void existsAuth() {
    	String user = permissionRepository.findById(auth)
    			.map(Permission::getPermissionId)
    			.orElse(null);
    	assertNull(user);
    }
}