package br.com.zipext.plr.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.ColaboradorDTO;
import br.com.zipext.plr.enums.EnumProperty;
import br.com.zipext.plr.enums.EnumXLSArea;
import br.com.zipext.plr.enums.EnumXLSSheets;
import br.com.zipext.plr.enums.EnumXLSTemplates;
import br.com.zipext.plr.export.impl.XlsFileExport;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.TemplateModel;
import br.com.zipext.plr.repository.ColaboradorRepository;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository repository;

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private TemplateCampoService templateCampoService;

	public ByteArrayInputStream export() throws IOException {
		XlsFileExport export = new XlsFileExport(
				this.propertyService.getProperty(EnumProperty.XLS_TEMPLATE_COLABORADORES_PATH),
				EnumXLSSheets.COLABORADORES);
		byte emptyBuff[] = new byte[] {};

		List<ColaboradorModel> models = this.repository.findAll();
		if (models != null && !models.isEmpty()) {
			export.processTable(models,
					this.templateCampoService.findByTemplateAndArea(
							new TemplateModel(EnumXLSTemplates.COLABORADORES.getCodigo()),
							EnumXLSArea.COLABORADORES.getArea()));
			return export.writeToFile();
		}

		return new ByteArrayInputStream(emptyBuff);
	}

	@Transactional(readOnly = true)
	public List<ColaboradorModel> findAll() {
		List<ColaboradorModel> colabs = repository.findAll();
		colabs.sort(Comparator.comparing(ColaboradorModel::getNome));
		return colabs;
	}

	@Transactional(readOnly = true)
	public Page<ColaboradorDTO> findByFilter(
			String matricula,
			String cpf,
			String nome,
			String situacao,
			String cargo,
			String diretoria,
			String time,
			String superiorImediato,
			int page, int size) {

		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
		
		Page<ColaboradorModel> models = this.repository.findByFilter(matricula, cpf, nome, situacao, cargo, diretoria, time, superiorImediato, pageRequest);
		Page<ColaboradorDTO> dtos = models.map(x -> new ColaboradorDTO(x));
		return dtos;
	}

	@Transactional(readOnly = true)
	public ColaboradorModel findByMatricula(String matricula) {
		return this.repository.findByMatricula(matricula);
	}

	@Transactional(readOnly = false)
	public ColaboradorModel save(ColaboradorModel model) {
		return this.repository.save(model);
	}
}
