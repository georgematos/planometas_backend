package br.com.zipext.plr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.RelAvaliacaoProjetoModel;
import br.com.zipext.plr.model.RelAvaliacaoProjetoModel.RelAvaliacaoProjetoPK;

@Repository
public interface RelAvaliacaoProjetoRepository extends JpaRepository<RelAvaliacaoProjetoModel, RelAvaliacaoProjetoModel.RelAvaliacaoProjetoPK> {
	Optional<RelAvaliacaoProjetoModel> findById(RelAvaliacaoProjetoPK pk);
}
