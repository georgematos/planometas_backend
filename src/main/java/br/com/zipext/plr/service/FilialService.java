package br.com.zipext.plr.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.FilialDTO;
import br.com.zipext.plr.enums.EnumProperty;
import br.com.zipext.plr.enums.EnumXLSArea;
import br.com.zipext.plr.enums.EnumXLSSheets;
import br.com.zipext.plr.enums.EnumXLSTemplates;
import br.com.zipext.plr.export.impl.XlsFileExport;
import br.com.zipext.plr.model.FilialModel;
import br.com.zipext.plr.model.TemplateModel;
import br.com.zipext.plr.repository.FilialRepository;
import br.com.zipext.plr.utils.PLRUtils;
import br.com.zipext.plr.utils.validators.Validator;

@Service
public class FilialService {

	@Autowired
	private FilialRepository repository;

	@Autowired
	private Validator<FilialModel> validator;

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private TemplateCampoService templateCampoService;

	@Transactional(readOnly = true)
	public List<FilialModel> findAllByOrderByNomeAsc() {
		return this.repository.findAllByOrderByNomeAsc();
	}

	@Transactional(readOnly = true)
	public List<FilialModel> findByFilter(Long id, String nome) {
		return repository.findByFilter(id, nome.toUpperCase());
	}

	@Transactional(readOnly = true)
	public FilialModel findById(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public FilialModel save(FilialModel model) throws Exception {
		validator.validar(model);

		return repository.save(model);
	}

	@Transactional(readOnly = false)
	public FilialModel update(Long id, FilialDTO dto) throws Exception {
		validator.validar(dto.obterModel());

		FilialModel entity = repository.findById(id).get();
		updateEntity(entity, dto);

		return repository.save(entity);
	}

	private void updateEntity(FilialModel entity, FilialDTO dto) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome().toUpperCase());
		entity.setAlteracao(LocalDateTime.now());
		entity.setResponsavelAlteracao(PLRUtils.SYS_USER);
	}

	public ByteArrayInputStream export() throws IOException {
		String template = propertyService.getProperty(EnumProperty.XLS_TEMPLATE_FILIAIS_PATH);
		XlsFileExport export = new XlsFileExport(template, EnumXLSSheets.FILIAIS);
		byte emptyBuff[] = new byte[] {};

		List<FilialModel> models = this.repository.findAll();
		if (models != null && !models.isEmpty()) {
			export.processTable(models, this.templateCampoService.findByTemplateAndArea(
					new TemplateModel(EnumXLSTemplates.FILIAIS.getCodigo()), EnumXLSArea.FILIAIS.getArea()));
			return export.writeToFile();
		}

		return new ByteArrayInputStream(emptyBuff);
	}
}
