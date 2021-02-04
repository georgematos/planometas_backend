package br.com.zipext.plr.service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.TipoMetaDTO;
import br.com.zipext.plr.model.TipoMetaModel;
import br.com.zipext.plr.repository.TipoMetaRepository;
import br.com.zipext.plr.utils.PLRUtils;
import br.com.zipext.plr.utils.validators.TipoMetaValidator;

@Service
public class TipoMetaService {

	@Autowired
	private TipoMetaRepository repository;

	@Autowired
	private TipoMetaValidator validator;

	@Transactional(readOnly = true)
	public List<TipoMetaModel> findAllByOrderByDescricaoAsc() {
		return this.repository.findAllByOrderByDescricaoAsc();
	}

	@Transactional(readOnly = true)
	public List<TipoMetaModel> findByFilter(Long id, String descricao, String abreviacao, String restrita) {

		descricao = StringUtils.isBlank(descricao) ? null : descricao.toUpperCase();
		restrita = StringUtils.isBlank(restrita) ? null : restrita.toUpperCase();
		abreviacao = StringUtils.isBlank(abreviacao) ? null : abreviacao.toUpperCase();

		return repository.findByFilter(id, descricao, abreviacao, restrita);
	}

	@Transactional(readOnly = true)
	public TipoMetaModel findById(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public TipoMetaModel save(TipoMetaModel model) throws Exception {
		validator.validar(model);

		return repository.save(model);
	}

	@Transactional(readOnly = false)
	public TipoMetaModel update(Long id, TipoMetaDTO dto) throws Exception {
		validator.validar(dto.obterModel());

		TipoMetaModel entity = repository.findById(id).get();
		updateEntity(entity, dto);

		return repository.save(entity);
	}

	private void updateEntity(TipoMetaModel entity, TipoMetaDTO dto) {
		entity.setId(dto.getId());
		entity.setDescricao(dto.getDescricao().toUpperCase());
		entity.setAbreviacao(dto.getAbreviacao().toUpperCase());
		entity.setIsMetaRestrita(dto.getIsMetaRestrita());
		entity.setAlteracao(LocalDateTime.now());
		entity.setResponsavelAlteracao(PLRUtils.SYS_USER);
	}
}
