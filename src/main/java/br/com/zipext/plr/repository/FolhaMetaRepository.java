package br.com.zipext.plr.repository;

import java.util.List;

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
		 + "join fetch model.responsavel resp "
		 + "join fetch model.inicioVigencia ini "
		 + "join fetch model.fimVigencia fim "
		 + "where (:matricula is null or colab.matricula = :matricula) "
		 + "and (:situacao is null or model.situacao = :situacao) "
		 + "and (:inicioVigencia is null or ini.id >= :inicioVigencia) "
		 + "and (:fimVigencia is null or fim.id <= :fimVigencia) "
		 + "and (:colaborador is null or colab.nome like %:colaborador%) "
		 + "and (:responsavel is null or resp.nome like %:responsavel%) "
		 + "order by colab.nome asc")
	public List<FolhaMetaModel> findByFilter(@Param("matricula") String matricula, 
											 @Param("inicioVigencia") Long skyInicioVigencia, 
											 @Param("fimVigencia") Long skyFimVigencia,
											 @Param("colaborador") String colaborador,
											 @Param("responsavel") String responsavel,
											 @Param("situacao") String situacao);
	
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
	
	@Modifying
	@Query("update FolhaMetaModel model "
	   	+ "set model.situacao = :situacao "
	   	+ "where model.id = :id")
	public void updateSituacaoById(@Param("id") Long id, @Param("situacao") String situacao);
}
