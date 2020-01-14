package br.com.zipext.plr.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.enums.EnumProperty;
import br.com.zipext.plr.enums.EnumSituacao;
import br.com.zipext.plr.enums.EnumXLSArea;
import br.com.zipext.plr.enums.EnumXLSSheets;
import br.com.zipext.plr.enums.EnumXLSTemplates;
import br.com.zipext.plr.export.impl.XlsFileExport;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FolhaMetaModel;
import br.com.zipext.plr.model.TemplateModel;
import br.com.zipext.plr.repository.FolhaMetaRepository;

@Service
public class FolhaMetaService {

	@Autowired
	private FolhaMetaRepository repository;
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private TemplateCampoService templateCampoService;
	
	@Modifying
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		this.repository.deleteById(id);
	}
	
	public ByteArrayInputStream export(Long idFolhaMeta) throws IOException {
		XlsFileExport export = new XlsFileExport(this.propertyService.getProperty(EnumProperty.XLS_TEMPLATE_FOLHA_META_PATH), EnumXLSSheets.FOLHA_METAS);
		Optional<FolhaMetaModel> optionalFolhaMeta = this.findById(idFolhaMeta);
		byte emptyBuff[] = new byte[] {};
		
		if (optionalFolhaMeta.isPresent()) {
			FolhaMetaModel folhaMeta = optionalFolhaMeta.get();
			
			export.processField(folhaMeta, this.templateCampoService.findByTemplateAndArea(
					new TemplateModel(EnumXLSTemplates.FOLHA_METAS.getCodigo()), EnumXLSArea.FOLHA_META.getArea()));
			export.processTable(folhaMeta.getFolhaMetaItems(), this.templateCampoService.findByTemplateAndArea(
					new TemplateModel(EnumXLSTemplates.FOLHA_METAS.getCodigo()), EnumXLSArea.ITEM_FOLHA.getArea()));
			
			return 
					export.writeToFile();
		}
		
		return
				new ByteArrayInputStream(emptyBuff);
	}
	
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
	
	@Modifying
	@Transactional(readOnly = false)
	public void updateSituacaoById(Long id, String situacao) {
		this.repository.updateSituacaoById(id, situacao);
	}
}
