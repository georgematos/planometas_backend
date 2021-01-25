package br.com.zipext.plr.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.EquivalenciaModel;

@Repository
public interface EquivalenciaRepository extends JpaRepository<EquivalenciaModel, Long> {

	public List<EquivalenciaModel> findAllByOrderByDescricaoAsc();
	
	@Query("select equivalencia from EquivalenciaModel equivalencia "
			 + "where (:id is null or equivalencia.id = :id) "
			 + "and (:descricao is null or equivalencia.descricao like %:descricao%) "
			 + "and (:multiplicador is null or equivalencia.multiplicador = :multiplicador) "
			 + "and (:limiteMultiplicador is null or equivalencia.limiteMultiplicador = :limiteMultiplicador) "
			 + "and (:limiteSomaMetas is null or equivalencia.limiteSomaMetas = :limiteSomaMetas) "
			 + "order by equivalencia.descricao asc")
	public Optional<List<EquivalenciaModel>> findByFilter(
			@Param("id") Long id,
			@Param("descricao") String descricao,
			@Param("multiplicador") BigDecimal multiplicador,
			@Param("limiteMultiplicador") BigDecimal limiteMultiplicador,
			@Param("limiteSomaMetas") BigDecimal limiteSomaMetas);

	public Optional<EquivalenciaModel> findByDescricao(String descricao);
}
