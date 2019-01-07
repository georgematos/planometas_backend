package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.ColaboradorMetaEspecificaModel;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.MetaEspecificaModel;

@Repository
public interface ColaboradorMetaEspecificaRepository extends JpaRepository<ColaboradorMetaEspecificaModel, ColaboradorMetaEspecificaModel.ColaboradorMetaEspecificaModelPK> {

	@Modifying
	@Query("delete from ColaboradorMetaEspecificaModel model"
			+ " where model.pk.colaborador = :colaborador"
			+ " and model.pk.metaEspecifica = :metaEspecifica")
	public void deleteColaboradorMeta(@Param("colaborador") ColaboradorModel colaborador, @Param("metaEspecifica") MetaEspecificaModel metaEspecifica);
	
	@Query("select model from ColaboradorMetaEspecificaModel model"
			+ " join fetch model.pk.colaborador colab"
			+ " where colab.matricula = :matricula" )
	public List<ColaboradorMetaEspecificaModel> findByMatricula(@Param("matricula") String matricula);
}
