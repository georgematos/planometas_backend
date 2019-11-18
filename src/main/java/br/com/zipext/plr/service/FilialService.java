package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.FilialModel;
import br.com.zipext.plr.repository.FilialRepository;

@Service
public class FilialService {

	@Autowired
	private FilialRepository repository;

	@Transactional(readOnly = true)
	public List<FilialModel> findAllByOrderByNomeAsc() {
		return this.repository.findAllByOrderByNomeAsc();
	}
}
