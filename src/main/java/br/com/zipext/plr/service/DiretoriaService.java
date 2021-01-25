package br.com.zipext.plr.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.DiretoriaDTO;
import br.com.zipext.plr.model.DiretoriaModel;
import br.com.zipext.plr.repository.DiretoriaRepository;
import br.com.zipext.plr.utils.PLRUtils;
import br.com.zipext.plr.utils.validators.DiretoriaValidator;

@Service
public class DiretoriaService {

	@Autowired
	private DiretoriaRepository repository;
	
	@Autowired
	private DiretoriaValidator validator;
	
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
	
}
