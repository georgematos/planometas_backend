package br.com.zipext.plr.service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.FrequenciaMedicaoDTO;
import br.com.zipext.plr.model.FrequenciaMedicaoModel;
import br.com.zipext.plr.repository.FrequenciaMedicaoRepository;
import br.com.zipext.plr.utils.PLRUtils;
import br.com.zipext.plr.utils.validators.FrequenciaMedicaoValidator;

@Service
public class FrequenciaMedicaoService {

	@Autowired
	private FrequenciaMedicaoRepository repository;
	
	@Autowired
	private FrequenciaMedicaoValidator validator;
	
	@Transactional(readOnly = true)
	public List<FrequenciaMedicaoModel> findAllByOrderByDescricaoAsc() {
		return
				this.repository.findAllByOrderByDescricaoAsc();
	}
	
	@Transactional(readOnly = true)
	public List<FrequenciaMedicaoModel> findByFilter(Long id, String descricao) {

		descricao = StringUtils.isBlank(descricao) ? null : descricao.toUpperCase();

		return repository.findByFilter(id, descricao);
	}

	@Transactional(readOnly = true)
	public FrequenciaMedicaoModel findById(Long id) {
		return repository.findById(id).get();
	}
	
	@Transactional(readOnly = true)
	public FrequenciaMedicaoModel findByDescricao(String descricao) {
		return repository.findByDescricao(descricao).get();
	}

	@Transactional(readOnly = false)
	public FrequenciaMedicaoModel save(FrequenciaMedicaoModel model) throws Exception {
		validator.validar(model);

		return repository.save(model);
	}

	@Transactional(readOnly = false)
	public FrequenciaMedicaoModel update(Long id, FrequenciaMedicaoDTO dto) throws Exception {
		validator.validar(dto.obterModel());

		FrequenciaMedicaoModel entity = repository.findById(id).get();
		updateEntity(entity, dto);

		return repository.save(entity);
	}

	private void updateEntity(FrequenciaMedicaoModel entity, FrequenciaMedicaoDTO dto) {
		entity.setId(dto.getId());
		entity.setDescricao(dto.getDescricao().toUpperCase());
		entity.setAlteracao(LocalDateTime.now());
		entity.setResponsavelAlteracao(PLRUtils.SYS_USER);
	}
}
