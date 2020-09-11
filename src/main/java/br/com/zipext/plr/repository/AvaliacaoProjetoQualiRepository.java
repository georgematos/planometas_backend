package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.AvaliacaoProjetoQualiModel;

@Repository
public interface AvaliacaoProjetoQualiRepository extends JpaRepository<AvaliacaoProjetoQualiModel, Long> {

	public List<AvaliacaoProjetoQualiModel> findAllByOrderByDescricaoAsc();
}
