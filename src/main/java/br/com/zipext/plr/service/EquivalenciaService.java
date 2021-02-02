package br.com.zipext.plr.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.EquivalenciaDTO;
import br.com.zipext.plr.enums.EnumProperty;
import br.com.zipext.plr.enums.EnumXLSArea;
import br.com.zipext.plr.enums.EnumXLSSheets;
import br.com.zipext.plr.enums.EnumXLSTemplates;
import br.com.zipext.plr.export.impl.XlsFileExport;
import br.com.zipext.plr.model.EquivalenciaModel;
import br.com.zipext.plr.model.TemplateModel;
import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.repository.EquivalenciaRepository;
import br.com.zipext.plr.utils.PLRUtils;
import br.com.zipext.plr.utils.validators.Validator;

@Service
public class EquivalenciaService {

	@Autowired
	private EquivalenciaRepository repository;

	@Autowired
	Validator<EquivalenciaModel> validator;
	
	@Autowired
	private PropertyService propertyService;

	@Autowired
	private TemplateCampoService templateCampoService;

	@Transactional(readOnly = true)
	public EquivalenciaModel findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Equivalência não encontrada."));
	}

	@Transactional(readOnly = true)
	public List<EquivalenciaModel> findAllByOrderByDescricaoAsc() {
		return this.repository.findAllByOrderByDescricaoAsc();
	}

	@Transactional(readOnly = true)
	public List<EquivalenciaModel> findByFilter(Long id, String descricao, BigDecimal multiplicador,
			BigDecimal limiteMultiplicador, BigDecimal limiteSomaMetas) {
		List<EquivalenciaModel> equivalencias = this.repository
				.findByFilter(id, descricao, multiplicador, limiteMultiplicador, limiteSomaMetas)
				.orElseThrow(() -> new EntityNotFoundException());
		return equivalencias;
	}

	@Transactional(readOnly = false)
	public EquivalenciaModel save(EquivalenciaModel model) throws Exception {

		validator.validar(model);

		// relaciona equivalencia e tempo (N/I) será alterado no futuro
		TempoModel tempo = new TempoModel();
		tempo.setId(-1L);
		model.setTempo(tempo);

		return repository.save(model);

	}

	@Transactional(readOnly = false)
	public EquivalenciaModel update(Long id, EquivalenciaDTO dto) throws Exception {
		validator.validar(dto.obterModel());
		EquivalenciaModel entity = repository.findById(id).get();
		updateEntity(entity, dto);
		return repository.save(entity);
	}

	private void updateEntity(EquivalenciaModel entity, EquivalenciaDTO dto) {
		entity.setId(dto.getId());
		entity.setDescricao(dto.getDescricao().toUpperCase());
		entity.setMultiplicador(dto.getMultiplicador());
		entity.setLimiteMultiplicador(dto.getLimiteMultiplicador());
		entity.setLimiteSomaMetas(dto.getLimiteSomaMetas());
		entity.setAlteracao(LocalDateTime.now());
		entity.setResponsavelAlteracao(PLRUtils.SYS_USER);
	}
	
	public ByteArrayInputStream export() throws IOException {
		String template = propertyService.getProperty(EnumProperty.XLS_TEMPLATE_EQUIVALENCIAS_PATH);
		XlsFileExport export = new XlsFileExport(template, EnumXLSSheets.EQUIVALENCIAS);
		byte emptyBuff[] = new byte[] {};

		List<EquivalenciaModel> models = this.repository.findAll();
		if (models != null && !models.isEmpty()) {
			export.processTable(models, this.templateCampoService.findByTemplateAndArea(
					new TemplateModel(EnumXLSTemplates.EQUIVALENCIAS.getCodigo()), EnumXLSArea.EQUIVALENCIAS.getArea()));
			return export.writeToFile();
		}

		return new ByteArrayInputStream(emptyBuff);
	}
}
