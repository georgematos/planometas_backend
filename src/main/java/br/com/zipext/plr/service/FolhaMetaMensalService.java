package br.com.zipext.plr.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.enums.EnumProperty;
import br.com.zipext.plr.enums.EnumXLSArea;
import br.com.zipext.plr.enums.EnumXLSSheets;
import br.com.zipext.plr.enums.EnumXLSTemplates;
import br.com.zipext.plr.export.impl.XlsFileExport;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FolhaMetaItemModel;
import br.com.zipext.plr.model.FolhaMetaMensalModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.TemplateModel;
import br.com.zipext.plr.repository.FolhaMetaMensalRepository;

@Service
public class FolhaMetaMensalService {

	@Autowired
	private FolhaMetaMensalRepository repository;

	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private TemplateCampoService templateCampoService;

	@Transactional(readOnly = true)
	public Long countByMeta(MetasModel meta) {
		return this.repository.countByMeta(meta);
	}

	@Modifying
	@Transactional(readOnly = false)
	public void deleteAll(List<FolhaMetaMensalModel> models) {
		this.repository.deleteAll(models);
	}

	@Transactional(readOnly = false)
	public void deleteByMeta(MetasModel meta) {
		this.repository.deleteByMeta(meta);
	}

	@Transactional(readOnly = false)
	public void deleteByMetaColaboradorAndAno(MetasModel meta, ColaboradorModel colaborador, Integer ano) {
		this.repository.deleteByMetaColaboradorAndAno(meta, colaborador, ano);
	}

	@Transactional(readOnly = true)
	public List<FolhaMetaMensalModel> findAll() {
		return this.repository.findAll();
	}

	@Transactional(readOnly = true)
	public List<FolhaMetaMensalModel> findByMetaColaboradorAndAno(MetasModel meta, ColaboradorModel colaborador,
			Integer ano) {
		return this.repository.findByMetaColaboradorAndAno(meta, colaborador, ano);
	}

	@Transactional(readOnly = true)
	public List<FolhaMetaMensalModel> findByFolhaMetaItem(FolhaMetaItemModel folhaMetaItem) {
		List<FolhaMetaMensalModel> models = this.repository.findByMetaAndAno(folhaMetaItem.getMeta(),
				folhaMetaItem.getFolhaMeta().getInicioVigencia().getAno());

		return models;
	}

	@Transactional(readOnly = false)
	public List<FolhaMetaMensalModel> saveAll(List<FolhaMetaMensalModel> models) {
		models.stream().forEach((x) -> {
			x.setResponsavelAlteracao(x.getColaboradorMeta().getMatricula());
			x.setAlteracao(LocalDateTime.now());
		});
		return this.repository.saveAll(models);
	}
	
	@Transactional(readOnly = false)
	public Optional<FolhaMetaMensalModel> findById(Long metaId) {
		return this.repository.findById(metaId);
	}
	
	public ByteArrayInputStream export(Long inicioVigencia, Long fimVigencia) throws IOException {
		XlsFileExport export = new XlsFileExport(
				this.propertyService.getProperty(EnumProperty.XLS_TEMPLATE_FOLHAS_METAS_MENSAIS_PATH),
				EnumXLSSheets.FOLHAS_METAS_MENSAIS);
		byte emptyBuff[] = new byte[] {};

		List<FolhaMetaMensalModel> models = this.repository.findByPeriodo(inicioVigencia, fimVigencia);
		if (models != null && !models.isEmpty()) {
			export.processTable(models,
					this.templateCampoService.findByTemplateAndArea(
							new TemplateModel(EnumXLSTemplates.FOLHAS_METAS_MENSAIS.getCodigo()),
							EnumXLSArea.FOLHAS_METAS_MENSAIS.getArea()));
			return export.writeToFile();
		}

		return new ByteArrayInputStream(emptyBuff);
	}
}
