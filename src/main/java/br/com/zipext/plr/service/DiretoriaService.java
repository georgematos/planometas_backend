package br.com.zipext.plr.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.DiretoriaDTO;
import br.com.zipext.plr.enums.EnumProperty;
import br.com.zipext.plr.enums.EnumXLSArea;
import br.com.zipext.plr.enums.EnumXLSSheets;
import br.com.zipext.plr.enums.EnumXLSTemplates;
import br.com.zipext.plr.export.impl.XlsFileExport;
import br.com.zipext.plr.model.DiretoriaModel;
import br.com.zipext.plr.model.TemplateModel;
import br.com.zipext.plr.repository.DiretoriaRepository;
import br.com.zipext.plr.utils.PLRUtils;
import br.com.zipext.plr.utils.validators.DiretoriaValidator;

@Service
public class DiretoriaService {

	@Autowired
	private DiretoriaRepository repository;

	@Autowired
	private DiretoriaValidator validator;

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private TemplateCampoService templateCampoService;

	@Transactional(readOnly = true)
	public List<DiretoriaModel> findAllByOrderByNomeAsc() {
		return repository.findAllByOrderByNomeAsc();
	}

	@Transactional(readOnly = true)
	public List<DiretoriaModel> findByFilter(Long id, String nome) {
		return repository.findByFilter(id, nome.toUpperCase());
	}

	@Transactional(readOnly = true)
	public DiretoriaModel findById(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public DiretoriaModel save(DiretoriaModel model) throws Exception {
		validator.validar(model);

		return repository.save(model);
	}

	@Transactional(readOnly = false)
	public DiretoriaModel update(Long id, DiretoriaDTO dto) throws Exception {
		validator.validar(dto.obterModel());

		DiretoriaModel entity = repository.findById(id).get();
		updateEntity(entity, dto);

		return repository.save(entity);
	}

	private void updateEntity(DiretoriaModel entity, DiretoriaDTO dto) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome().toUpperCase());
		entity.setAlteracao(LocalDateTime.now());
		entity.setResponsavelAlteracao(PLRUtils.SYS_USER);
	}

	public ByteArrayInputStream export() throws IOException {
		String template = propertyService.getProperty(EnumProperty.XLS_TEMPLATE_DIRETORIAS_PATH);
		XlsFileExport export = new XlsFileExport(template, EnumXLSSheets.DIRETORIAS);
		byte emptyBuff[] = new byte[] {};

		List<DiretoriaModel> models = this.repository.findAll();
		if (models != null && !models.isEmpty()) {
			export.processTable(models, this.templateCampoService.findByTemplateAndArea(
					new TemplateModel(EnumXLSTemplates.DIRETORIAS.getCodigo()), EnumXLSArea.DIRETORIAS.getArea()));
			return export.writeToFile();
		}

		return new ByteArrayInputStream(emptyBuff);
	}

}
