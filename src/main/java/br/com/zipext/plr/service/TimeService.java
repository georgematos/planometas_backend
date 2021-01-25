package br.com.zipext.plr.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.TimeDTO;
import br.com.zipext.plr.model.TimeModel;
import br.com.zipext.plr.repository.TimeRepository;
import br.com.zipext.plr.utils.PLRUtils;
import br.com.zipext.plr.utils.validators.Validator;

@Service
public class TimeService {

	@Autowired
	private TimeRepository repository;

	@Autowired
	private Validator<TimeModel> validator;

	@Transactional(readOnly = true)
	public List<TimeModel> findAllByOrderByNomeAsc() {
		return this.repository.findAllByOrderByNomeAsc();
	}

	@Transactional(readOnly = true)
	public List<TimeModel> findByFilter(String codigo, String nome) {
		if (StringUtils.isBlank(nome))
			codigo.toUpperCase();
		return repository.findByFilter(codigo, nome.toUpperCase());
	}

	@Transactional(readOnly = true)
	public TimeModel findByCod(String codigo) {
		return repository.findById(codigo).get();
	}

	@Transactional(readOnly = false)
	public TimeModel save(TimeModel model) throws Exception {
		validator.validar(model);

		boolean exists = repository.existsById(model.getCodigo());

		if (!exists) {
			return repository.save(model);
		} else {
			throw new EntityExistsException("Um time com o código " + model.getCodigo() + " já existe.");
		}

	}

	@Transactional(readOnly = false)
	public TimeModel update(String codigo, TimeDTO dto) throws Exception {
		validator.validar(dto.obterModel());

		TimeModel entity = repository.findById(codigo).get();
		updateEntity(entity, dto);

		return repository.save(entity);
	}

	private void updateEntity(TimeModel entity, TimeDTO dto) {
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome().toUpperCase());
		entity.setAlteracao(LocalDateTime.now());
		entity.setResponsavelAlteracao(PLRUtils.SYS_USER);
	}
}
