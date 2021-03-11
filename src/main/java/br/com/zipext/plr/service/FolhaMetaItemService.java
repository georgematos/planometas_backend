package br.com.zipext.plr.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.enums.EnumProperty;
import br.com.zipext.plr.enums.EnumXLSArea;
import br.com.zipext.plr.enums.EnumXLSSheets;
import br.com.zipext.plr.enums.EnumXLSTemplates;
import br.com.zipext.plr.export.impl.XlsFileExport;
import br.com.zipext.plr.model.FolhaMetaItemModel;
import br.com.zipext.plr.model.FolhaMetaModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.TemplateModel;
import br.com.zipext.plr.repository.FolhaMetaItemRepository;

@Service
public class FolhaMetaItemService {

	@Autowired
	private FolhaMetaItemRepository repository;
	
	@Autowired
	private PropertyService propertyService;

	@Autowired
	private TemplateCampoService templateCampoService;
	
	@Transactional(readOnly = true)
	public Long countByMeta(MetasModel meta) {
		return
				this.repository.countByMeta(meta);
	}
	
	@Transactional(readOnly = true)
	public List<FolhaMetaItemModel> findByMetaAndPeriodo(Long inicioVigencia,
			Long fimVigencia, Long indicadorId) {
		List<FolhaMetaItemModel> folhasItens = repository.findByMetaAndPeriodo(indicadorId, inicioVigencia, fimVigencia);
		return folhasItens;
	}
	
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
	
	public ByteArrayInputStream exportConsultas(Long inicioVigencia, Long fimVigencia, Long indicadorId) throws IOException {
		XlsFileExport export = new XlsFileExport(this.propertyService.getProperty(EnumProperty.XLS_TEMPLATE_CONSULTA_FOLHAS_METAS_PATH), EnumXLSSheets.CONSULTA_FOLHAS_METAS);
		byte emptyBuff[] = new byte[] {};
		
		List<FolhaMetaItemModel> folhasDeMeta = this.findByMetaAndPeriodo(inicioVigencia, fimVigencia, indicadorId);
		if (folhasDeMeta != null && !folhasDeMeta.isEmpty()) {
			
			export.processTable(
				folhasDeMeta,
				this.templateCampoService.findByTemplateAndArea(
					new TemplateModel(EnumXLSTemplates.CONSULTA_FOLHAS_METAS.getCodigo()),
					EnumXLSArea.CONSULTAS_FOLHAS_METAS.getArea()
				)
			);
			
			return export.writeToFile();
		}

		return new ByteArrayInputStream(emptyBuff);
	}

}
