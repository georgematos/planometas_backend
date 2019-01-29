package br.com.zipext.plr.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.enums.EnumProperty;
import br.com.zipext.plr.export.impl.XlsFileExport;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.repository.ColaboradorRepository;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository repository;
	
	@Autowired
	private PropertyService propertyService;
	
	public ByteArrayInputStream export(ColaboradorModel colaborador) throws IOException {
		XlsFileExport export = new XlsFileExport(this.propertyService.getProperty(EnumProperty.XLS_TEMPLATE_PATH));
		return
				export.processXlsForColaborador(colaborador);
	}
	
	@Transactional(readOnly = true)
	public List<ColaboradorModel> findAll() {
		return
				this.repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public ColaboradorModel findByMatricula(String matricula) {
		return
				this.repository.findByMatricula(matricula);
	}
	
}
