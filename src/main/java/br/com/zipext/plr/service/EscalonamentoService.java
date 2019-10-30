package br.com.zipext.plr.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.EscalonamentoModel;
import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.repository.EscalonamentoRepository;

@Service
public class EscalonamentoService {

	@Autowired
	private EscalonamentoRepository repository;
	
	@Transactional(readOnly = true)
	public List<EscalonamentoModel> findAll() {
		return
				this.repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<EscalonamentoModel> findEscalonamentoByAtingidoAndTipoMedicao(BigDecimal valMetaAtingido, TipoMedicaoModel tipoMedicao) {
		return
				this.repository.findEscalonamentoByAtingidoAndTipoMedicao(valMetaAtingido, tipoMedicao);
	}
	
	public Optional<EscalonamentoModel> findBestFitEscalonamentoByAtingidoAndTipoMedicao(BigDecimal valMetaAtingido, TipoMedicaoModel tipoMedicao) {
		return
				this.findEscalonamentoByAtingidoAndTipoMedicao(valMetaAtingido, tipoMedicao).stream()
																							  .max((esc1, esc2) -> esc1.getFaixa().compareTo(esc2.getFaixa()));
		
	}
}
