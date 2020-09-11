package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.AvaliacaoProjetoPrazoModel;

@Repository
public interface AvaliacaoProjetoPrazoRepository extends JpaRepository<AvaliacaoProjetoPrazoModel, Long> {

	public List<AvaliacaoProjetoPrazoModel> findAllByOrderByDescricaoAsc();
}
