package br.com.recatalog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.recatalog.app.model.CatalogItem;

@Repository
public interface CatalogRepository extends JpaRepository<CatalogItem,String> {
}
