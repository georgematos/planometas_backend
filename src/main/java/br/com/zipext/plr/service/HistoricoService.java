package br.com.zipext.plr.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.enums.EnumProperty;
import br.com.zipext.plr.export.impl.XlsFileExport;
import br.com.zipext.plr.model.ColaboradorMetaEspecificaModel;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.HistoricoMetaEspecificaModel;
import br.com.zipext.plr.model.HistoricoModel;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.repository.HistoricoRepository;

@Service
public class HistoricoService {
	
	@Autowired
	private HistoricoRepository repository;
	
	@Autowired
	private ColaboradorMetaEspecificaService colabMetaEspecificaService;
	
	@Autowired
	private PropertyService propertyService;
	
	public ByteArrayInputStream export(ColaboradorModel colaborador, Long version) throws IOException {
		XlsFileExport export = new XlsFileExport(this.propertyService.getProperty(EnumProperty.XLS_TEMPLATE_PATH));
		return
				export.processXlsForColaborador(this.filterVersion(colaborador, version));
	}
	
	@Transactional(readOnly = true)
	public List<HistoricoModel> findByResponsavel(String matriculaResponsavel) {
		return
				this.repository.findByResponsavel(new UsuarioModel(matriculaResponsavel));
	}
	
	@Transactional(readOnly = true)
	public List<HistoricoModel> findHistoricoByMatricula(String matricula) {
		return
				this.repository.findByColaborador(new ColaboradorModel(matricula));
	}
	
	public HistoricoModel geraHistorico(HistoricoModel model) {
		List<ColaboradorMetaEspecificaModel> metas = this.colabMetaEspecificaService.findByMatricula(model.getColaborador().getMatricula());
		if (!metas.isEmpty()) {
			Set<HistoricoMetaEspecificaModel> historicoMetaEspecifica = new HashSet<>();
			metas.forEach(m -> historicoMetaEspecifica.add(new HistoricoMetaEspecificaModel(model, m)));
			
			model.setHistoricoMetaEspecifica(historicoMetaEspecifica);
		}
		
		model.setVersao(this.getVersion(model.getColaborador()) + 1);

		
		return 
				this.save(model);
	}
	
	@Transactional(readOnly = true)
	public Long getVersion(ColaboradorModel colaborador) {
		return 
				this.repository.countByColaborador(colaborador);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public HistoricoModel save(HistoricoModel model) {
		return
				this.repository.save(model);
	}
	
	private ColaboradorModel filterVersion(ColaboradorModel colaborador, Long version) {
		Optional<HistoricoModel> historico = colaborador.getHistorico().stream().filter(hist -> hist.getVersao().equals(version)).findFirst();
		if (historico.isPresent()) {
			colaborador.setHistoricoExport(historico.get());
		}

		return
				colaborador;
	}
}
