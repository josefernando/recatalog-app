package br.com.recatalog.app.security.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.recatalog.app.security.auth.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,String> {
}