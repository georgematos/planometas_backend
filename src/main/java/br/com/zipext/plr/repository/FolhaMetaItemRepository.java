package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.FolhaMetaItemModel;
import br.com.zipext.plr.model.FolhaMetaModel;
import br.com.zipext.plr.model.MetasModel;

@Repository
public interface FolhaMetaItemRepository extends JpaRepository<FolhaMetaItemModel, Long> {

	public Long countByMeta(MetasModel meta);
	
	@Modifying
	@Query("delete from FolhaMetaItemModel model "
		+  "where model.folhaMeta.id = :idFolhaMeta")
	public void deleteByIdFolhaMeta(@Param("idFolhaMeta") Long idFolhaMeta);
	
	public List<FolhaMetaItemModel> findByFolhaMetaOrderBySequenciaAsc(FolhaMetaModel folhaMeta);
	
//	@Query("select model from FolhaMetaItemModel model "
////			+  "join model.folhaMeta.colaborador colab "
//			+  "join model.folhaMeta.inicioVigencia ini "
//			+  "join model.folhaMeta.fimVigencia fim "
//			+  "left join model.folhaMeta fm "			
////			+  "where colab = :colaborador "
//			+  "where ini.id >= :inicioVigencia "
//			+  "and fim.id <= :fimVigencia "
//			+  "and model.meta.id = :metaId "
//			+  "and fm is not null "
//			+  "order by model.meta.descricao asc")
	
	
//	@Query("select model from FolhaMetaItemModel model "
//			+  "join model.folhaMeta fm "
////			+  "join model.folhaMeta.inicioVigencia ini "
////			+  "join model.folhaMeta.fimVigencia fim "
//			+  "on model.meta.id = :metaId "
//			+  "and fm.inicioVigencia.id >= :inicioVigencia "
//			+  "and fm.fimVigencia.id <= :fimVigencia "
//			+  "order by model.meta.descricao asc")
//	public List<FolhaMetaItemModel> findByMetaAndPeriodo(
//			@Param("metaId") Long metaId,
////			@Param("colaborador") ColaboradorModel colababorador,
//			@Param("inicioVigencia") Long inicioVigencia,
//			@Param("fimVigencia") Long fimVigencia
//	);
	
	@Query("select model from FolhaMetaItemModel model "			
	+  "inner join model.folhaMeta fm "
	+  "where model.meta.id = :metaId "
	+  "and model.folhaMeta.inicioVigencia.id >= :inicioVigencia "
	+  "and model.folhaMeta.fimVigencia.id <= :fimVigencia "
	+  "order by model.meta.descricao asc")
public List<FolhaMetaItemModel> findByMetaAndPeriodo(
	@Param("metaId") Long metaId,
//	@Param("colaborador") ColaboradorModel colababorador,
	@Param("inicioVigencia") Long inicioVigencia,
	@Param("fimVigencia") Long fimVigencia
);
}
