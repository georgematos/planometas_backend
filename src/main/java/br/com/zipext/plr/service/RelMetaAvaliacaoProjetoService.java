package br.com.zipext.plr.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.enums.EnumProperty;
import br.com.zipext.plr.enums.EnumXLSArea;
import br.com.zipext.plr.enums.EnumXLSSheets;
import br.com.zipext.plr.enums.EnumXLSTemplates;
import br.com.zipext.plr.export.impl.XlsFileExport;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.RelMetaAvaliacaoProjetoModel;
import br.com.zipext.plr.model.TemplateModel;
import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.repository.RelMetaAvaliacaoProjetoRepository;

@Service
public class RelMetaAvaliacaoProjetoService {

	@Autowired
	private RelMetaAvaliacaoProjetoRepository repository;
	
	@Autowired
	private PropertyService propertyService;

	@Autowired
	private TemplateCampoService templateCampoService;

	@Transactional(readOnly = false)
	public void deleteByMetaAndPeriodo(MetasModel meta, Integer anoPeriodoPLR) {
		this.repository.deleteByMetaAndPeriodo(meta);
	}

	@Transactional(readOnly = true)
	public List<RelMetaAvaliacaoProjetoModel> findByResponsavelAndVigencia(ColaboradorModel responsavel,
			TempoModel dataAvaliacao) {
		return this.repository.findByResponsavelAndDataAvaliacao(responsavel, dataAvaliacao);
	}

	@Transactional(readOnly = false)
	public RelMetaAvaliacaoProjetoModel save(RelMetaAvaliacaoProjetoModel model) {
		return this.repository.save(model);
	}
	
	public ByteArrayInputStream export() throws IOException {
		String template = propertyService.getProperty(EnumProperty.XLS_TEMPLATE_AVALIACAO_QUALI_PATH);
		XlsFileExport export = new XlsFileExport(template, EnumXLSSheets.AVALIACAO_QUALI);
		byte emptyBuff[] = new byte[] {};

		List<RelMetaAvaliacaoProjetoModel> models = this.repository.findAll();
		if (models != null && !models.isEmpty()) {
			export.processTable(models, this.templateCampoService.findByTemplateAndArea(
					new TemplateModel(EnumXLSTemplates.AVALIACAO_QUALI.getCodigo()), EnumXLSArea.AVALIACAO_QUALI.getArea()));
			return export.writeToFile();
		}

		return new ByteArrayInputStream(emptyBuff);
	}
}
