package br.com.recatalog.app.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.recatalog.app.model.domain.CatalogItem;

@Repository
public interface CatalogRepository extends JpaRepository<CatalogItem,String> {
}
