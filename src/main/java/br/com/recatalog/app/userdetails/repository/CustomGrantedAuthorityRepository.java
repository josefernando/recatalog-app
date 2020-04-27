package br.com.recatalog.app.userdetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.recatalog.app.model.userdetails.CustomGrantedAuthority;

@Repository
public interface CustomGrantedAuthorityRepository extends JpaRepository<CustomGrantedAuthority,String> {
} 