package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.FolhaMetaItemModel;
import br.com.zipext.plr.model.FolhaMetaModel;
import br.com.zipext.plr.repository.FolhaMetaItemRepository;

@Service
public class FolhaMetaItemService {

	@Autowired
	private FolhaMetaItemRepository repository;
	
	@Modifying
	@Transactional(readOnly = false)
	public void deleteByIdFolhaMeta(Long idFolhaMeta) {
		this.repository.deleteByIdFolhaMeta(idFolhaMeta);
	}
	
	@Transactional(readOnly = true)
	public List<FolhaMetaItemModel> findByFolhaMeta(FolhaMetaModel folhaMeta) {
		return
				this.repository.findByFolhaMetaOrderBySequenciaAsc(folhaMeta);
	}
	
	@Transactional(readOnly = false)
	public List<FolhaMetaItemModel> saveAll(List<FolhaMetaItemModel> models) {
		return
				this.repository.saveAll(models);
	}
}
