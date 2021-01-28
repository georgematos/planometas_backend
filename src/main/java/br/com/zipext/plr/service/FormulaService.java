package br.com.zipext.plr.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.FormulaDTO;
import br.com.zipext.plr.enums.EnumSituacao;
import br.com.zipext.plr.model.FormulaModel;
import br.com.zipext.plr.repository.FormulaRepository;
import br.com.zipext.plr.utils.PLRUtils;
import br.com.zipext.plr.utils.validators.FormulaValidator;

@Service
public class FormulaService {

	@Autowired
	private FormulaRepository repository;
	
	@Autowired
	private FormulaValidator validator;

	@Transactional(readOnly = true)
	public List<FormulaModel> findAllAtivosByOrderByNomeAsc() {
		return this.repository.findAllAtivosByOrderByNomeAsc(EnumSituacao.ATIVO.getCodigo().toString());
	}
	
	@Transactional(readOnly = true)
	public List<FormulaModel> findByFilter(Long id, String nome, String descricao, String situacao) {
		return this.repository.findByFilter(id, nome, descricao, situacao);
	}
	
	@Transactional(readOnly = true)
	public FormulaModel findByNome(String nome) {

		boolean isPresent = repository.findByNome(nome).isPresent();

		if(isPresent)
			return this.repository.findByNome(nome).get();

		return null;
	}

	@Transactional(readOnly = false)
	public FormulaModel save(FormulaModel formula) throws Exception {

		validator.validar(formula);

		formula.setNome(formula.getNome().toUpperCase());
		formula.setEvalFormula(formula.getEvalFormula());
		formula.setSituacao(selecionaSituacao(formula.getSituacao()));

		return this.repository.save(formula);
	}

	@Transactional(readOnly = false)
	public FormulaModel update(Long id, FormulaDTO dto) throws Exception {
		
		validator.validar(dto.obterModel());
		
		FormulaModel entity = repository.findById(id).get();
		updateEntity(entity, dto);
		return repository.save(entity);
	}

	private void updateEntity(FormulaModel entity, FormulaDTO dto) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setEvalFormula(dto.getEvalFormula());
		entity.setSituacao(selecionaSituacao(dto.getSituacao()));
		entity.setAlteracao(LocalDateTime.now());
		entity.setResponsavelAlteracao(PLRUtils.SYS_USER);
	}

	private String selecionaSituacao(String situacao) {
		switch (situacao) {
		case "A":
			return EnumSituacao.ATIVO.getValue();
		case "I":
			return EnumSituacao.INATIVO.getValue();
		default:
			return null;
		}
	}
}
