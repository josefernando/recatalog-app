package br.com.recatalog.app.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.recatalog.app.model.security.SpringUsers;


@Repository
public interface SpringUsersRepository extends JpaRepository<SpringUsers,String> {
}