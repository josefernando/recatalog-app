package br.com.recatalog.app.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.recatalog.app.model.security.SpringAuthorities;

@Repository
public interface SpringAuthoritiesRepository extends JpaRepository<SpringAuthorities,String> {
}