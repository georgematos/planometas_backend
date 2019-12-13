package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.enums.EnumQuantQual;
import br.com.zipext.plr.model.FormulaModel;
import br.com.zipext.plr.model.FrequenciaMedicaoModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.model.TipoMetaModel;

@Repository
public interface MetasRepository extends JpaRepository<MetasModel, Long> {

	@Query("select model from MetasModel model "
		 + "where model.situacao = :situacao "
		 + "order by model.descricao asc")
	public List<MetasModel> findAllAtivasByOrderByDescricaoAsc(@Param("situacao") String situacao);
	
	public MetasModel findByDescricaoAndSituacao(String descricao, String situacao);
	
	@Query("select model from MetasModel model "
		 + "join fetch model.formula formula "
		 + "join fetch model.tipoMeta tipoMeta "
		 + "join fetch model.tipoMedicao tipoMedicao "
		 + "join fetch model.frequenciaMedicao frequenciaMedicao "
		 + "where (:situacao is null or model.situacao = :situacao ) "
		 + "and (:metaModel is null or model = :metaModel)"
		 + "and (:meta is null or model.descricao like %:meta%) "
		 + "and (:tipoMedicao is null or tipoMedicao = :tipoMedicao) "
		 + "and (:tipoMeta is null or tipoMeta = :tipoMeta) "
		 + "and (:formula is null or formula = :formula) "
		 + "and (:frequenciaMedicao is null or frequenciaMedicao = :frequenciaMedicao) "
		 + "order by model.descricao asc")
	public List<MetasModel> findByFilter(
				@Param("metaModel") MetasModel model,
				@Param("meta") String meta, 
				@Param("situacao") String situacao, 
				@Param("tipoMedicao") TipoMedicaoModel tipoMedicao, 
				@Param("tipoMeta") TipoMetaModel tipoMeta, 
				@Param("formula") FormulaModel formula, 
				@Param("frequenciaMedicao") FrequenciaMedicaoModel frequenciaMedicao);
	
	@Query("select model from MetasModel model "
		  + "where model.isQuantitativa = :isQuantitativa")
	public List<MetasModel> findQuantitativas(@Param("isQuantitativa") String isQuantitativa);
}
