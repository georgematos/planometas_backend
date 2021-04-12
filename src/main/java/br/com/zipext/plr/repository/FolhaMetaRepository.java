package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FolhaMetaModel;

@Repository
public interface FolhaMetaRepository extends JpaRepository<FolhaMetaModel, Long> {
	
	@Query("select model from FolhaMetaModel model "
		 + "join fetch model.colaborador colab "
		 + "join fetch model.cargo cargo "
		 + "join fetch model.responsavel resp "
		 + "join fetch model.inicioVigencia ini "
		 + "join fetch model.fimVigencia fim "
		 + "where (:matricula is null or colab.matricula = :matricula) "
		 + "and (:situacao is null or model.situacao = :situacao) "
		 + "and (:inicioVigencia is null or ini.id >= :inicioVigencia) "
		 + "and (:fimVigencia is null or fim.id <= :fimVigencia) "
		 + "and (:colaborador is null or colab.nome like %:colaborador%) "
		 + "and (:cargo is null or cargo.nome like %:cargo%) "
		 + "and (:responsavel is null or resp.nome like %:responsavel%) "
		 + "and (:superiorImediato is null or model.superiorImediato.matricula = :superiorImediato) "
		 + "and (:filial is null or model.filial.id = :filial) "
		 + "and (:time is null or model.time.codigo = :time) "
		 + "and (:diretoria is null or model.diretoria.id = :diretoria) "
		 + "order by colab.nome asc")
	public List<FolhaMetaModel> findByFilter(
			 @Param("matricula") String matricula, 
			 @Param("cargo") String cargo,
			 @Param("inicioVigencia") Long skyInicioVigencia, 
			 @Param("fimVigencia") Long skyFimVigencia,
			 @Param("colaborador") String colaborador,
			 @Param("responsavel") String responsavel,
			 @Param("situacao") String situacao,
			 @Param("superiorImediato") String superiorImediato,
			 @Param("filial") Long filial,
			 @Param("time") String time,
			 @Param("diretoria") Long diretoria
	);

	@Query("select model from FolhaMetaModel model "
		+  "join fetch model.colaborador colab "
		+  "join fetch model.inicioVigencia ini "
		+  "join fetch model.fimVigencia fim "
		+  "where colab = :colaborador "
		+  "and ini.id >= :inicioVigencia "
		+  "and fim.id <= :fimVigencia "
		+  "and model.situacao = :situacao "
		+  "order by colab.nome asc")
	public List<FolhaMetaModel> findByColaboradorAndVigencia(@Param("colaborador") ColaboradorModel colaborador, 
			@Param("inicioVigencia") Long inicioVigencia, 
			@Param("fimVigencia") Long fimVigencia,
			@Param("situacao") String situacao);
	
	@Query(value = "select model from FolhaMetaModel model "
			+  "join fetch model.colaborador colab "
			+  "join fetch model.inicioVigencia ini "
			+  "join fetch model.fimVigencia fim "
			+  "where colab = :colaborador "
			+  "and ini.id >= :inicioVigencia "
			+  "and fim.id <= :fimVigencia "
			+  "and model.situacao = :situacao "
			+  "order by colab.nome asc",
			countQuery = "select count(model) from FolhaMetaModel model "
					+  "join model.colaborador colab "
					+  "join model.inicioVigencia ini "
					+  "join model.fimVigencia fim "
					+  "where colab = :colaborador "
					+  "and ini.id >= :inicioVigencia "
					+  "and fim.id <= :fimVigencia "
					+  "and model.situacao = :situacao "
					+  "group by model.id ")
		public Page<FolhaMetaModel> findByColaboradorAndVigenciaPage(@Param("colaborador") ColaboradorModel colaborador, 
				@Param("inicioVigencia") Long inicioVigencia, 
				@Param("fimVigencia") Long fimVigencia,
				@Param("situacao") String situacao,
				Pageable pageable);
	
	@Query("select model from FolhaMetaModel model "
			+  "join fetch model.responsavel resp "
			+  "join fetch model.inicioVigencia ini "
			+  "join fetch model.fimVigencia fim "
			+  "where (:responsavel is null or resp = :responsavel) "
			+  "and ini.id >= :inicioVigencia "
			+  "and fim.id <= :fimVigencia "
			+  "and (:situacao is null or model.situacao = :situacao) "
			+  "order by resp.nome asc")
	public List<FolhaMetaModel> findByResponsavelAndVigencia(
			@Param("responsavel") ColaboradorModel responsavel,
			@Param("inicioVigencia") Long inicioVigencia, 
			@Param("fimVigencia") Long fimVigencia,
			@Param("situacao") String situacao);
	
	@Query(value = "select model from FolhaMetaModel model "
			+  "join fetch model.responsavel resp "
			+  "join fetch model.inicioVigencia ini "
			+  "join fetch model.fimVigencia fim "
			+  "where (:responsavel is null or resp = :responsavel) "
			+  "and (:folha is null or model.id = :folha) "
			+  "and (:colaborador is null or model.colaborador.nome like %:colaborador%) "
			+  "and (:cargo is null or model.cargo.nome like %:cargo%) "
			+  "and ini.id >= :inicioVigencia "
			+  "and fim.id <= :fimVigencia "
			+  "and (:situacao is null or model.situacao = :situacao) "
			+  "order by resp.nome asc",
			countQuery = "select count(model) from FolhaMetaModel model "
					+  "join model.responsavel resp "
					+  "join model.inicioVigencia ini "
					+  "join model.fimVigencia fim "
					+  "where (:responsavel is null or resp = :responsavel) "
					+  "and (:folha is null or model.id = :folha) "
					+  "and (:colaborador is null or model.colaborador.nome like %:colaborador%) "
					+  "and (:cargo is null or model.cargo.nome like %:cargo%) "
					+  "and ini.id >= :inicioVigencia "
					+  "and fim.id <= :fimVigencia "
					+  "and (:situacao is null or model.situacao = :situacao) "
					+  "group by model.id")
	public Page<FolhaMetaModel> findByResponsavelAndVigenciaPage(
			@Param("folha") Long folha,
			@Param("colaborador") String colaborador,
			@Param("cargo") String cargo,			
			@Param("situacao") String situacao,
			@Param("inicioVigencia") Long inicioVigencia, 
			@Param("fimVigencia") Long fimVigencia,
			@Param("responsavel") ColaboradorModel responsavel,
			Pageable pageable);
	
	@Modifying
	@Query("update FolhaMetaModel model "
	   	+ "set model.situacao = :situacao "
	   	+ "where model.id = :id")
	public void updateSituacaoById(@Param("id") Long id, @Param("situacao") String situacao);
}
