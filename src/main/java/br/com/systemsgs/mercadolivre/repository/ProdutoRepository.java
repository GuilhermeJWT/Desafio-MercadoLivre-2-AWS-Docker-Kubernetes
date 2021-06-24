package br.com.systemsgs.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.systemsgs.mercadolivre.model.ModelProduto;

@Repository
public interface ProdutoRepository extends JpaRepository<ModelProduto, Long>{

}
