package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.MetasModel;

@Repository
public interface MetasRepository extends JpaRepository<MetasModel, Long> {

	public MetasModel findByDescricaoAndSituacao(String descricao, String situacao);
	
	@Query("select model from MetasModel model "
		 + "join fetch model.formula formula "
		 + "join fetch model.tipoMeta tipoMeta "
		 + "join fetch model.tipoMedicao tipoMedicao "
		 + "join fetch model.frequenciaMedicao frequenciaMedicao "
		 + "where (:situacao is null or model.situacao = :situacao )"
		 + "and (:meta is null or model.descricao like %:meta%) "
		 + "and (:tipoMedicao is null or tipoMedicao.descricao like %:tipoMedicao%) "
		 + "and (:tipoMeta is null or tipoMeta.descricao like %:tipoMeta%) "
		 + "and (:formula is null or formula.nome like %:formula%) "
		 + "and (:frequenciaMedicao is null or frequenciaMedicao.descricao like %:frequenciaMedicao%) "
		 + "order by model.descricao asc")
	public List<MetasModel> findByFilter(
				@Param("meta") String meta, 
				@Param("situacao") String situacao, 
				@Param("tipoMedicao") String tipoMedicao, 
				@Param("tipoMeta") String tipoMeta, 
				@Param("formula") String formula, 
				@Param("frequenciaMedicao") String frequenciaMedicao);
}
