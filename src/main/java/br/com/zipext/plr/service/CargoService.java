package br.com.zipext.plr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repository;
}
