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
	

	@Query("select model from FolhaMetaItemModel model "			
	+  "inner join model.folhaMeta fm "
	+  "where model.meta.id = :metaId "
	+  "and model.folhaMeta.inicioVigencia.id >= :inicioVigencia "
	+  "and model.folhaMeta.fimVigencia.id <= :fimVigencia "
	+  "order by model.meta.descricao asc")
	public List<FolhaMetaItemModel> findByMetaAndPeriodo(
		@Param("metaId") Long metaId,
		@Param("inicioVigencia") Long inicioVigencia,
		@Param("fimVigencia") Long fimVigencia
);

	@Query("select model from FolhaMetaItemModel model "
			+ "where model.folhaMeta.id = :id "
			+ "order by model.id asc")
	public List<FolhaMetaItemModel> findByFolhaDeMetaId(@Param("id") Long id);

}
