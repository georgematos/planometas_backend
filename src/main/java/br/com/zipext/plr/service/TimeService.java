package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.TimeModel;
import br.com.zipext.plr.repository.TimeRepository;

@Service
public class TimeService {

	@Autowired
	private TimeRepository repository;
	
	@Transactional(readOnly = true)
	public List<TimeModel> findAllByOrderByNomeAsc() {
		return this.repository.findAllByOrderByNomeAsc();
	}
}
