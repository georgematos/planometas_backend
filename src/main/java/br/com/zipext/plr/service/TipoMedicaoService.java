package br.com.zipext.plr.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.TipoMedicaoDTO;
import br.com.zipext.plr.enums.EnumProperty;
import br.com.zipext.plr.enums.EnumXLSArea;
import br.com.zipext.plr.enums.EnumXLSSheets;
import br.com.zipext.plr.enums.EnumXLSTemplates;
import br.com.zipext.plr.export.impl.XlsFileExport;
import br.com.zipext.plr.model.TemplateModel;
import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.repository.TipoMedicaoRepository;
import br.com.zipext.plr.utils.PLRUtils;
import br.com.zipext.plr.utils.validators.TipoMedicaoValidator;

@Service
public class TipoMedicaoService {

	@Autowired
	private TipoMedicaoRepository repository;

	@Autowired
	private TipoMedicaoValidator validator;
	@Autowired
	private PropertyService propertyService;

	@Autowired
	private TemplateCampoService templateCampoService;

	@Transactional(readOnly = true)
	public List<TipoMedicaoModel> findAllByOrderByDescricaoAsc() {
		return this.repository.findAllByOrderByDescricaoAsc();
	}

	@Transactional(readOnly = true)
	public List<TipoMedicaoModel> findByFilter(Long id, String descricao) {

		descricao = StringUtils.isBlank(descricao) ? null : descricao.toUpperCase();

		return repository.findByFilter(id, descricao);
	}

	@Transactional(readOnly = true)
	public TipoMedicaoModel findById(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public TipoMedicaoModel findByDescricao(String descricao) {
		return repository.findByDescricao(descricao).get();
	}

	@Transactional(readOnly = false)
	public TipoMedicaoModel save(TipoMedicaoModel model) throws Exception {
		validator.validar(model);

		return repository.save(model);
	}

	@Transactional(readOnly = false)
	public TipoMedicaoModel update(Long id, TipoMedicaoDTO dto) throws Exception {
		validator.validar(dto.obterModel());

		TipoMedicaoModel entity = repository.findById(id).get();
		updateEntity(entity, dto);

		return repository.save(entity);
	}

	private void updateEntity(TipoMedicaoModel entity, TipoMedicaoDTO dto) {
		entity.setId(dto.getId());
		entity.setDescricao(dto.getDescricao().toUpperCase());
		entity.setAlteracao(LocalDateTime.now());
		entity.setResponsavelAlteracao(PLRUtils.SYS_USER);
	}

	public ByteArrayInputStream export() throws IOException {
		String template = propertyService.getProperty(EnumProperty.XLS_TEMPLATE_TIPOS_MEDICAO_PATH);
		XlsFileExport export = new XlsFileExport(template, EnumXLSSheets.TIPOS_MEDICAO);
		byte emptyBuff[] = new byte[] {};

		List<TipoMedicaoModel> models = this.repository.findAll();
		if (models != null && !models.isEmpty()) {
			export.processTable(models, this.templateCampoService.findByTemplateAndArea(
					new TemplateModel(EnumXLSTemplates.TIPOS_MEDICAO.getCodigo()), EnumXLSArea.TIPOS_MEDICAO.getArea()));
			return export.writeToFile();
		}

		return new ByteArrayInputStream(emptyBuff);
	}
}
