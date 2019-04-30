package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.HistoricoMetaEspecificaMensalModel;

@Repository
public interface HistoricoMetaMensalRepository extends JpaRepository<HistoricoMetaEspecificaMensalModel, HistoricoMetaEspecificaMensalModel.HistoricoMetaEspecificaMensalPK> {

	@Modifying
	@Transactional
	@Query("delete from HistoricoMetaEspecificaMensalModel model"
			+ " where model.pk.historico.id= ?1")
	void deleteByIdHistorico(Long idHistorico);
	
	@Query("select model from HistoricoMetaEspecificaMensalModel model "
			+ "where model.pk.metaEspecificaMensal.pk.colaboradorMetaEspecifica.pk.metaEspecifica.id = :idMeta "
			+ "and model.pk.metaEspecificaMensal.pk.colaboradorMetaEspecifica.pk.colaborador.matricula = :matricula "
			+ "and model.pk.metaEspecificaMensal.pk.colaboradorMetaEspecifica.pk.sequencia = :sequencia "
			+ "and model.pk.historico.versao = :version "
			+ "order by model.pk.metaEspecificaMensal.pk.mes.numMes asc")
	public List<HistoricoMetaEspecificaMensalModel> 
			findByFilter(@Param("idMeta") Long idMeta, @Param("matricula") String matricula,
						 @Param("sequencia") Integer sequencia, @Param("version") Long version);
}
