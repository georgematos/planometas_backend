package br.com.zipext.plr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.enums.EnumSituacao;
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
	public List<FolhaMetaModel> findByFilter(String matricula, Long skyInicioVigencia, Long skyFimVigencia, String colaborador, String responsavel, String situacao) {
		return
				this.repository.findByFilter(matricula, skyInicioVigencia, skyFimVigencia, colaborador, responsavel, situacao);
	}
	
	@Transactional(readOnly = true)
	public Optional<FolhaMetaModel> findById(Long id) {
		return
				this.repository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<FolhaMetaModel> findByColaboradorAndVigencia(ColaboradorModel colaborador, Long inicioVigencia, Long fimVigencia) {
		return
				this.repository.findByColaboradorAndVigencia(colaborador, inicioVigencia, fimVigencia, EnumSituacao.ATIVO.getCodigo().toString());
	}
	
	@Transactional(readOnly = true)
	public List<FolhaMetaModel> findByResponsavelAndVigencia(ColaboradorModel responsavel, Long inicioVigencia, Long fimVigencia) {
		return 
				this.repository.findByResponsavelAndVigencia(responsavel, inicioVigencia, fimVigencia, null);
	}
	
	@Transactional(readOnly = true)
	public List<FolhaMetaModel> findPendentesByColaboradorAndVigencia(ColaboradorModel colaborador, Long inicioVigencia, Long fimVigencia) {
		return
				this.repository.findByColaboradorAndVigencia(colaborador, inicioVigencia, fimVigencia, EnumSituacao.PENDENTE.getCodigo().toString());
	}
	
	@Transactional(readOnly = true)
	public List<FolhaMetaModel> findAllPendentesByVigencia(Long inicioVigencia, Long fimVigencia) {
		return
				this.repository.findByResponsavelAndVigencia(null, inicioVigencia, fimVigencia, EnumSituacao.PENDENTE.getCodigo().toString());
	}
	
	@Transactional(readOnly = false)
	public FolhaMetaModel save(FolhaMetaModel folhaMeta) {
		return
				this.repository.save(folhaMeta);
	}
}
