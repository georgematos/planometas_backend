package br.com.zipext.plr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, String> {

	public UsuarioModel findByLogin(String login);
	
	@Modifying
	@Query("update UsuarioModel model set model.inPrimeiroAcesso = :inPrimeiroAcesso "
		 + "where model.login = :login")
	public void redefinePrimeiroAcesso(@Param("login") String login, @Param("inPrimeiroAcesso") String inPrimeiroAcesso);
}
