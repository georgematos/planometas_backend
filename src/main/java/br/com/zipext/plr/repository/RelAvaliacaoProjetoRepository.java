package br.com.zipext.plr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.RelAvaliacaoProjetoModel;

@Repository
public interface RelAvaliacaoProjetoRepository extends JpaRepository<RelAvaliacaoProjetoModel, RelAvaliacaoProjetoModel.RelAvaliacaoProjetoPK> {

}
