package br.com.zipext.plr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.repository.MetaGeralRepository;

@Service
public class MetaGeralService {

	@Autowired
	private MetaGeralRepository repository;
}
