package br.com.zipext.plr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FolhaMetaModel;
import br.com.zipext.plr.repository.FolhaMetaRepository;

@Service
public class FolhaMetaService {

	@Autowired
	private FolhaMetaRepository repository;
	
	@Transactional(readOnly = true)
	public List<FolhaMetaModel> findAll() {
		return
				this.repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<FolhaMetaModel> findById(Long id) {
		return
				this.repository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<FolhaMetaModel> findByColaborador(ColaboradorModel colaborador) {
		return
				this.repository.findByColaborador(colaborador);
	}
	
	@Transactional(readOnly = false)
	public FolhaMetaModel save(FolhaMetaModel folhaMeta) {
		return
				this.repository.save(folhaMeta);
	}
}
