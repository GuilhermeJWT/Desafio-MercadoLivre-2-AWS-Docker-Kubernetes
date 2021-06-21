package br.com.systemsgs.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.systemsgs.mercadolivre.model.ModelUsuario;

@Repository
public interface UsuarioRepository extends JpaRepository<ModelUsuario, Long>{
	
	@Query("SELECT u FROM ModelUsuario u WHERE u.login =:login")
	ModelUsuario findByLogin(@Param("login") String login);

}
