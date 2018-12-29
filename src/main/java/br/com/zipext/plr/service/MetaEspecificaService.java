package br.com.zipext.plr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.repository.MetaEspecificaRepository;

@Service
public class MetaEspecificaService {

	@Autowired
	private MetaEspecificaRepository repository;
}
