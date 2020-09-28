package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.enums.EnumQuantQual;
import br.com.zipext.plr.model.MetasPeriodoModel;
import br.com.zipext.plr.repository.MetasPeriodoRepository;

@Service
public class MetasPeriodoService {

	@Autowired
	private MetasPeriodoRepository repository;
	
	@Transactional(readOnly = true)
	public Long countMetasByPeriodo(Integer periodoPLR) {
		return
				this.repository.countMetasByPeriodo(periodoPLR);
	}
	
	@Modifying
	@Transactional(readOnly = false)
	public void delete(MetasPeriodoModel model) {
		this.repository.delete(model);
	}
	
	@Transactional(readOnly = true)
	public List<MetasPeriodoModel> findAll() {
		return
				this.repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<MetasPeriodoModel> findAllByPeriodo(Long periodoPLR, Integer page) {
		return
				this.repository.findAllByPeriodo(periodoPLR);
	}
	
	@Transactional(readOnly = true)
	public List<MetasPeriodoModel> findMetasByPeriodoAndSituacao(Long periodoPLR, String situacao, Integer page) {
		Integer pageSize = page == null ? 2000 : page;
		return
				this.repository.findMetasByPeriodoAndSituacao(
						periodoPLR, situacao, PageRequest.of(0, pageSize));
	}
	
	@Transactional(readOnly = true)
	public List<MetasPeriodoModel> findMetasQuantitativasByPeriodoAndSituacao(Long periodoPLR, String situacao, Integer page) {
		Integer pageSize = page == null ? 2000 : page;
		return
				this.repository.findMetasQuantitativasByPeriodoAndSituacao(
						EnumQuantQual.QUANTITATIVA.getCodigo(), 
						periodoPLR, situacao, PageRequest.of(0, pageSize));
	}
	
	
	@Transactional(readOnly = false)
	public MetasPeriodoModel save(MetasPeriodoModel model) {
		return
				this.repository.save(model);
	}
}
