package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.RelMetaAvaliacaoProjetoModel;
import br.com.zipext.plr.model.TempoModel;

@Repository
public interface RelMetaAvaliacaoProjetoRepository extends JpaRepository<RelMetaAvaliacaoProjetoModel, RelMetaAvaliacaoProjetoModel.RelMetaAvaliacaoProjetoPK> {

	public List<RelMetaAvaliacaoProjetoModel> findByResponsavelAndDataAvaliacao(ColaboradorModel responsavel, TempoModel dataAvaliacao);
}
