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
}
