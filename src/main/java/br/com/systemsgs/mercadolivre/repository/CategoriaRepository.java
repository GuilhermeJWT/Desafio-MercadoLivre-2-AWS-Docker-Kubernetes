package br.com.systemsgs.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.systemsgs.mercadolivre.model.ModelCategoria;

@Repository
public interface CategoriaRepository extends JpaRepository<ModelCategoria, Long>{

}
