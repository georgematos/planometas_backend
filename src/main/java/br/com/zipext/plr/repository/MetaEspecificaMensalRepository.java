package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.MetaEspecificaMensalModel;

@Repository
public interface MetaEspecificaMensalRepository extends JpaRepository<MetaEspecificaMensalModel, MetaEspecificaMensalModel.MetaEspecificaMensalPK> {

	@Query("select model from MetaEspecificaMensalModel model "
			+ "where model.pk.colaboradorMetaEspecifica.pk.colaborador.matricula = :matricula " 
			+ "and model.pk.colaboradorMetaEspecifica.pk.metaEspecifica.id = :idMeta "
			+ "and model.pk.colaboradorMetaEspecifica.pk.sequencia = :sequencia "
			+ "order by model.pk.mes.numMes asc")
	public List<MetaEspecificaMensalModel> findByFilter(@Param("idMeta") Long idMeta,
			@Param("matricula") String matricula,
			@Param("sequencia") Integer sequencia);
	
	@Query("select model from MetaEspecificaMensalModel model "
			+ "where model.pk.colaboradorMetaEspecifica.pk.colaborador.matricula = :matricula ")
	public List<MetaEspecificaMensalModel> findByMatricula(@Param("matricula") String matricula);
}
