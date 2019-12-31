package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.PerfilModel;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilModel, Long> {
	
	public List<PerfilModel> findBySituacaoOrderByNomeAsc(String situacao);
}
