package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.RelMetaAvaliacaoProjetoModel;
import br.com.zipext.plr.model.TempoModel;

@Repository
public interface RelMetaAvaliacaoProjetoRepository extends JpaRepository<RelMetaAvaliacaoProjetoModel, RelMetaAvaliacaoProjetoModel.RelMetaAvaliacaoProjetoPK> {

	@Modifying
	@Query("delete from RelMetaAvaliacaoProjetoModel model "
		+  "where model.pk.meta = :meta ")
	public void deleteByMetaAndPeriodo(@Param("meta") MetasModel meta);

	public List<RelMetaAvaliacaoProjetoModel> findByResponsavelAndDataAvaliacao(ColaboradorModel responsavel, TempoModel dataAvaliacao);
}
