package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.FolhaMetaItemModel;
import br.com.zipext.plr.model.FolhaMetaModel;

@Repository
public interface FolhaMetaItemRepository extends JpaRepository<FolhaMetaItemModel, Long> {

	public List<FolhaMetaItemModel> findByFolhaMetaOrderBySequenciaAsc(FolhaMetaModel folhaMeta);
}
