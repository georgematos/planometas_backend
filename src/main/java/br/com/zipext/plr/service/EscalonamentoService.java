package br.com.zipext.plr.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.EscalonamentoDTO;
import br.com.zipext.plr.model.EscalonamentoModel;
import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.repository.EscalonamentoRepository;
import br.com.zipext.plr.repository.TipoMedicaoRepository;
import br.com.zipext.plr.utils.PLRUtils;
import br.com.zipext.plr.utils.validators.EscalonamentoValidator;

@Service
public class EscalonamentoService {

	@Autowired
	private EscalonamentoRepository repository;
	
	@Autowired
	private TipoMedicaoRepository tipoMedicaoRepository;
	
	@Autowired
	private EscalonamentoValidator validator;

	@Transactional(readOnly = true)
	public List<EscalonamentoModel> findAll() {
		return this.repository.findAll();
	}

	@Transactional(readOnly = true)
	public List<EscalonamentoModel> findEscalonamentoByAtingidoAndTipoMedicao(BigDecimal valMetaAtingido,
			TipoMedicaoModel tipoMedicao) {
		return this.repository.findEscalonamentoByAtingidoAndTipoMedicao(valMetaAtingido, tipoMedicao);
	}

	public Optional<EscalonamentoModel> findBestFitEscalonamentoByAtingidoAndTipoMedicao(BigDecimal valMetaAtingido,
			TipoMedicaoModel tipoMedicao) {
		return this.findEscalonamentoByAtingidoAndTipoMedicao(valMetaAtingido, tipoMedicao).stream()
				.max((esc1, esc2) -> esc1.getFaixa().compareTo(esc2.getFaixa()));

	}
	
	public List<EscalonamentoModel> findByFilter(Long id, Long tipoMedicaoId, BigDecimal faixa, BigDecimal desempenho) {
		return repository.findByFilter(id, tipoMedicaoId, faixa, desempenho);
	}
	
	public Optional<EscalonamentoModel> findById(Long id) {
		return repository.findById(id);
	}

	@Transactional(readOnly = false)
	public EscalonamentoModel save(EscalonamentoModel escalonamento) throws Exception {

		validator.validar(escalonamento);

		return this.repository.save(escalonamento);
	}

	@Transactional(readOnly = false)
	public EscalonamentoModel update(Long id, EscalonamentoDTO dto) throws Exception {
		
		validator.validar(dto.obterModel());
		
		EscalonamentoModel entity = repository.findById(id).get();
		updateEntity(entity, dto);
		return repository.save(entity);
	}

	private void updateEntity(EscalonamentoModel entity, EscalonamentoDTO dto) {
		entity.setId(dto.getId());
		entity.setTipoMedicao(tipoMedicaoRepository.findById(dto.getTipoMedicaoId()).get());
		entity.setFaixa(dto.getFaixa());
		entity.setDesempenho(dto.getDesempenho());
		entity.setAlteracao(LocalDateTime.now());
		entity.setResponsavelAlteracao(PLRUtils.SYS_USER);
	}
}
