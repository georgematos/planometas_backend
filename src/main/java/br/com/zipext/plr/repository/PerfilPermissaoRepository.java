package br.com.zipext.plr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.PerfilModel;
import br.com.zipext.plr.model.PerfilPermissaoModel;

@Repository
public interface PerfilPermissaoRepository extends JpaRepository<PerfilPermissaoModel, PerfilPermissaoModel.PerfilPermissaoPK> {

	@Query("select model from PerfilPermissaoModel model"
		 + " where model.pk.perfil = :perfil")
	public PerfilPermissaoModel findByPerfil(@Param("perfil") PerfilModel perfil);
}
