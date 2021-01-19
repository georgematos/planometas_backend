package br.com.zipext.plr.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.CargoNewDTO;
import br.com.zipext.plr.enums.EnumSituacao;
import br.com.zipext.plr.model.CargoModel;
import br.com.zipext.plr.model.EquivalenciaModel;
import br.com.zipext.plr.repository.CargoRepository;
import br.com.zipext.plr.repository.EquivalenciaRepository;
import br.com.zipext.plr.utils.PLRUtils;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repository;
	
	@Autowired
	private EquivalenciaRepository equivalenciaRepo;
	
	@Transactional(readOnly = true)
	public List<CargoModel> findAllByOrderByNomeAsc() {
		return this.repository.findAllByOrderByNomeAsc();
	}
	
	@Transactional(readOnly = true)
	public List<CargoModel> findByFilter(Long codigo, String nome, Long equivalencia, String situacao) {
		return this.repository.findByFilter(codigo, nome, equivalencia, situacao);
	}
	
	@Transactional(readOnly = true)
	public CargoModel findByNome(String nome) {
		return this.repository.findByNome(nome);
	}

	@Transactional(readOnly = false)
	public CargoModel save(CargoModel cargo) {
		EquivalenciaModel equivalencia = equivalenciaRepo.findById(cargo.getEquivalencia().getId()).get();
		cargo.setNome(cargo.getNome().toUpperCase());
		switch (cargo.getSituacao()) {
		case "A":
			cargo.setSituacao(EnumSituacao.ATIVO.getValue());
			break;
		case "I":
			cargo.setSituacao(EnumSituacao.INATIVO.getValue());
			break;
		default:
			cargo.setSituacao(null);
	}
		cargo.setEquivalencia(equivalencia);
		

		return this.repository.save(cargo);
	}

	@Transactional(readOnly = false)
	public CargoModel update(Long id, CargoNewDTO dto) {
		CargoModel entity = repository.findById(id).get();
		updateEntity(entity, dto);
		return repository.save(entity);
	}

	private void updateEntity(CargoModel entity, CargoNewDTO dto) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setEquivalencia(equivalenciaRepo.getOne(dto.getEquivalencia()));
		switch (dto.getSituacao()) {
			case "A":
				entity.setSituacao(EnumSituacao.ATIVO.getValue());
				break;
			case "I":
				entity.setSituacao(EnumSituacao.INATIVO.getValue());
				break;
			default:
				entity.setSituacao(null);
		}
		entity.setAlteracao(LocalDateTime.now());
		entity.setResponsavelAlteracao(PLRUtils.SYS_USER);
	}
}
