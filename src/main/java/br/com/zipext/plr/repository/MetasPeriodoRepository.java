package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.MetasPeriodoModel;
import br.com.zipext.plr.model.MetasPeriodoModel.MetasPeriodoPK;

@Repository
public interface MetasPeriodoRepository extends JpaRepository<MetasPeriodoModel, MetasPeriodoPK> {

	@Query("select model from MetasPeriodoModel model " 
			+ "where model.pk.metas.isQuantitativa = :isQuantitativa "
			+ "and model.pk.tempo.id = :periodoPLR " 
			+ "and (:situacao is null or model.situacao = :situacao) " 
			+ "order by model.pk.metas.descricao asc")
	public List<MetasPeriodoModel> findMetasQuantitativasByPeriodoAndSituacao(
			@Param("isQuantitativa") String isQuantitativa, @Param("periodoPLR") Long periodoPLR,
			@Param("situacao") String situacao, Pageable page);
	
	@Query("select model from MetasPeriodoModel model "
		 + "where model.pk.tempo.id = :periodoPLR "
		 + "and (:situacao is null or model.situacao = :situacao) "
		 + "order by model.pk.metas.descricao asc")
	public List<MetasPeriodoModel> findMetasByPeriodoAndSituacao(@Param("periodoPLR") Long periodoPLR, 
			@Param("situacao") String situacao,
			Pageable page);

	@Query("select model from MetasPeriodoModel model "
		 + "where model.pk.tempo.id = :periodoPLR "
		 + "order by model.pk.metas.descricao asc")
	public List<MetasPeriodoModel> findAllByPeriodo(@Param("periodoPLR") Long periodoPLR);
	
	@Query("select count(model) from MetasPeriodoModel model "
		 + "join fetch model.pk pk "
		 + "where pk.tempo.ano = :periodoPLR")
	public Long countMetasByPeriodo(@Param("periodoPLR") Integer periodoPLR);
}
