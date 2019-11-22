package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.repository.TipoMedicaoRepository;

@Service
public class TipoMedicaoService {

	@Autowired
	private TipoMedicaoRepository repository;
	
	@Transactional(readOnly = true)
	public List<TipoMedicaoModel> findAllByOrderByDescricaoAsc() {
		return
				this.repository.findAllByOrderByDescricaoAsc();
	}
}
