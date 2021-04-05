package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FormulaModel;
import br.com.zipext.plr.model.FrequenciaMedicaoModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.model.TipoMetaModel;

@Repository
public interface MetasRepository extends JpaRepository<MetasModel, Long> {

	@Query("select model from MetasModel model "
		+  "left join fetch model.metasPeriodo mp "
		+  "where mp.pk.tempo = :periodo "
		+  "order by model.descricao asc")
	public List<MetasModel> findAll(@Param("periodo") TempoModel periodo);
	
	@Query("select model from MetasModel model "
		 + "where model.situacao = :situacao "
		 + "order by model.descricao asc")
	public List<MetasModel> findAllAtivasByOrderByDescricaoAsc(@Param("situacao") String situacao);
	
	public List<MetasModel> findByDescricaoAndSituacao(String descricao, String situacao);
	
	@Query("select model from MetasModel model "
		 + "join fetch model.formula formula "
		 + "join fetch model.tipoMeta tipoMeta "
		 + "join fetch model.tipoMedicao tipoMedicao "
		 + "join fetch model.frequenciaMedicao frequenciaMedicao "
		 + "where (:situacao is null or model.situacao = :situacao ) "
		 + "and (:metaModel is null or model = :metaModel) "
		 + "and (:codigoLegado is null or model.codigoLegado = :codigoLegado) "
		 + "and (:meta is null or model.descricao like %:meta%) "
		 + "and (:tipoMedicao is null or tipoMedicao = :tipoMedicao) "
		 + "and (:tipoMeta is null or tipoMeta = :tipoMeta) "
		 + "and (:formula is null or formula = :formula) "
		 + "and (:frequenciaMedicao is null or frequenciaMedicao = :frequenciaMedicao) "
		 + "order by model.descricao asc")
	public List<MetasModel> findByFilter(
				@Param("metaModel") MetasModel model,
				@Param("codigoLegado") String codigoLegado,
				@Param("meta") String meta, 
				@Param("situacao") String situacao, 
				@Param("tipoMedicao") TipoMedicaoModel tipoMedicao, 
				@Param("tipoMeta") TipoMetaModel tipoMeta, 
				@Param("formula") FormulaModel formula, 
				@Param("frequenciaMedicao") FrequenciaMedicaoModel frequenciaMedicao);
	
	@Query("select model from MetasModel model "
		  + "where model.isQuantitativa = :isQuantitativa")
	public List<MetasModel> findByQualificadorMeta(@Param("isQuantitativa") String isQuantitativa);
	
	@Query("select model from MetasModel model "
		 + "join fetch model.prazo prazo "
		 + "join fetch model.tipoMeta tipoMeta "
		 + "where (:aprovador is null or model.aprovador = :aprovador)"
		 + "and tipoMeta.descricao in (:tiposMeta) "
		 + "and prazo.ano = :periodoPLR "
		 + "and prazo.id <= :skyDataLimite")
	public List<MetasModel> findProjetosVencidosByResponsavel(
			@Param("periodoPLR") Integer periodoPLR,
			@Param("skyDataLimite") Long skyDataLimite, 
			@Param("aprovador") ColaboradorModel aprovador,
			@Param("tiposMeta") List<String> tiposMeta);
	
	@Query(value = "SELECT cm.cd_meta as meta_id, ds_meta FROM metas.cad_meta cm "
					+ "LEFT JOIN metas.ass_periodo_meta pmm "
					+ "ON (cm.cd_meta = pmm.cd_meta) "
					+ "AND pmm.sky_periodo_meta = :periodoPLR "
					+ "ORDER BY cm.ds_meta", nativeQuery = true)
		public List<Object[]> findAllResumidoByPeriodo(@Param("periodoPLR") Long periodoPLR);
	
}

//select meta from MetasModel meta "
//+ "where meta.prazo.id = :periodoPLR "
//+ "order by meta.descricao asc
