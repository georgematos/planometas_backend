package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.ColaboradorMetaEspecificaModel;

@Repository
public interface ColaboradorMetaEspecificaRepository extends JpaRepository<ColaboradorMetaEspecificaModel, ColaboradorMetaEspecificaModel.ColaboradorMetaEspecificaModelPK> {
	
	@Modifying
	@Transactional
	@Query("delete from ColaboradorMetaEspecificaModel model"
			+ " where model.pk.colaborador.matricula = ?1"
			+ " and model.pk.metaEspecifica.id = ?2"
			+ " and model.pk.sequencia = ?3")
	public void deleteColaboradorMetaByFilter(String matricula, Long idMetaEspecifica, Integer sequencia);
	
	@Modifying
	@Transactional
	@Query("delete from ColaboradorMetaEspecificaModel model"
			+ " where model.pk.colaborador.matricula = ?1"
			+ " and model.pk.metaEspecifica.id = ?2")
	public void deleteColaboradorMeta(String matricula, Long idMetaEspecifica);
	
	@Query("select model from ColaboradorMetaEspecificaModel model"
			+ " join fetch model.pk.colaborador colab"
			+ " where colab.matricula = :matricula" )
	public List<ColaboradorMetaEspecificaModel> findByMatricula(@Param("matricula") String matricula);
}
