package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.AvaliacaoProjetoOrcModel;

@Repository
public interface AvaliacaoProjetoOrcRepository extends JpaRepository<AvaliacaoProjetoOrcModel, Long> {

	public List<AvaliacaoProjetoOrcModel> findAllByOrderByDescricaoAsc();
}
