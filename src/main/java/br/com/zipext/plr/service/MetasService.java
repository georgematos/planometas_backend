package br.com.zipext.plr.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.enums.EnumProperty;
import br.com.zipext.plr.enums.EnumQuantQual;
import br.com.zipext.plr.enums.EnumSituacao;
import br.com.zipext.plr.enums.EnumTipoMeta;
import br.com.zipext.plr.enums.EnumXLSArea;
import br.com.zipext.plr.enums.EnumXLSSheets;
import br.com.zipext.plr.enums.EnumXLSTemplates;
import br.com.zipext.plr.export.impl.XlsFileExport;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FormulaModel;
import br.com.zipext.plr.model.FrequenciaMedicaoModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.TemplateModel;
import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.model.TipoMetaModel;
import br.com.zipext.plr.repository.MetasRepository;

@Service
public class MetasService {

	@Autowired
	private MetasRepository repository;
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private TemplateCampoService templateCampoService;	
	
	public ByteArrayInputStream export(TempoModel periodo) throws IOException {
		XlsFileExport export = new XlsFileExport(this.propertyService.getProperty(EnumProperty.XLS_TEMPLATE_INDICADORES_PATH), EnumXLSSheets.INDICADORES);
		byte emptyBuff[] = new byte[] {};

		List<MetasModel> models = this.findAll(periodo);
		if (models != null && !models.isEmpty()) {
			export.processTable(models, 
					this.templateCampoService.findByTemplateAndArea(
							new TemplateModel(EnumXLSTemplates.INDICADORES.getCodigo()), EnumXLSArea.INDICADORES.getArea()));
			return
					export.writeToFile();
		}
	
		return new ByteArrayInputStream(emptyBuff);
	}
	
	@Transactional(readOnly = true)
	public List<MetasModel> findAllAtivasByOrderByDescricaoAsc() {
		return
				this.repository.findAllAtivasByOrderByDescricaoAsc(EnumSituacao.ATIVO.getCodigo().toString());
	}
	
	@Transactional(readOnly = true)
	public List<MetasModel> findAll(TempoModel periodo) {
		return this.repository.findAll(periodo);
	}
	
	@Transactional(readOnly = true)
	public List<MetasModel> findByDescricaoAndSituacao(String descricao, String situacao) {
		return
				this.repository.findByDescricaoAndSituacao(descricao, situacao);
	}
	
	@Transactional(readOnly = true)
	public List<MetasModel> findByFilter(MetasModel model, String codigoLegado, String meta, String situacao, TipoMedicaoModel tipoMedicao, TipoMetaModel tipoMeta,
			FormulaModel formula, FrequenciaMedicaoModel frequenciaMedicao) {
		return 
				this.repository.findByFilter(model, codigoLegado, meta, situacao, tipoMedicao, tipoMeta, formula, frequenciaMedicao);
	}
	
	@Transactional(readOnly = true)
	public MetasModel findById(Long id) {
		Optional<MetasModel> model = this.repository.findById(id);
		if (model.isPresent()) {
			return model.get();
		}
		
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<MetasModel> findProjetosVencidosByResponsavel(Integer periodoPLR, Long skyDataLimite, ColaboradorModel aprovador) {
		return 
			this.repository.findProjetosVencidosByResponsavel(periodoPLR, skyDataLimite, aprovador, Arrays.asList(EnumTipoMeta.PROJETO.name(), EnumTipoMeta.ENTREGA.name()));
	}

	@Transactional(readOnly = true)
	public List<MetasModel> findQualitativas() {
		return
				this.repository.findByQualificadorMeta(EnumQuantQual.QUALITATIVA.getCodigo().toString());
	}
	
	@Transactional(readOnly = true)
	public List<MetasModel> findQuantitativas() {
		return
				this.repository.findByQualificadorMeta(EnumQuantQual.QUANTITATIVA.getCodigo().toString());
	}
	
	@Transactional(readOnly = false)
	public MetasModel save(MetasModel model) {
		return this.repository.save(model);
	}
}
	