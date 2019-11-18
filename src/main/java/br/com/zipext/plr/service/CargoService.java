package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.CargoModel;
import br.com.zipext.plr.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repository;
	
	@Transactional(readOnly = true)
	public List<CargoModel> findAllByOrderByNomeAsc() {
		return this.repository.findAllByOrderByNomeAsc();
	}
}
