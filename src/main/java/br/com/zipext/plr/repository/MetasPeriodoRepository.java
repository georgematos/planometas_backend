package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.MetasPeriodoModel;
import br.com.zipext.plr.model.MetasPeriodoModel.MetasPeriodoPK;

@Repository
public interface MetasPeriodoRepository extends JpaRepository<MetasPeriodoModel, MetasPeriodoPK> {

	@Query("select model from MetasPeriodoModel model " + "join fetch model.pk.metas meta "
			+ "join fetch model.pk.tempo tempo " + "where meta.isQuantitativa = :isQuantitativa "
			+ "and tempo.id = :periodoPLR " + "and model.situacao = :situacao " + "order by meta.descricao asc")
	public List<MetasPeriodoModel> findMetasQuantitativasByPeriodoAndSituacao(
			@Param("isQuantitativa") String isQuantitativa, @Param("periodoPLR") Long periodoPLR,
			@Param("situacao") String situacao);

	@Query("select count(model) from MetasPeriodoModel model "
		 + "join fetch model.pk pk "
		 + "where pk.tempo.ano = :periodoPLR")
	public Long countMetasByPeriodo(@Param("periodoPLR") Integer periodoPLR);
}
