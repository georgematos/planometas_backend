package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.TipoMetaModel;
import br.com.zipext.plr.repository.TipoMetaRepository;

@Service
public class TipoMetaService {

	@Autowired
	private TipoMetaRepository repository;
	
	@Transactional(readOnly = true)
	public List<TipoMetaModel> findAllByOrderByDescricaoAsc() {
		return
				this.repository.findAllByOrderByDescricaoAsc();
	}
}
