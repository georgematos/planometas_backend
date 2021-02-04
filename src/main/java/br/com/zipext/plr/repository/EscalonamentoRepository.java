package br.com.zipext.plr.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.EscalonamentoModel;
import br.com.zipext.plr.model.TipoMedicaoModel;

@Repository
public interface EscalonamentoRepository extends JpaRepository<EscalonamentoModel, Long> {

	@Query("select model from EscalonamentoModel model "
		+  "join fetch model.tipoMedicao tipomed "
		+  "where tipomed = :tipoMedicao "
		+  "and :valMetaAtingido >= model.faixa "
		+  "order by model.faixa desc")
	public List<EscalonamentoModel> findEscalonamentoByAtingidoAndTipoMedicao
		(@Param("valMetaAtingido") BigDecimal valMetaAtingido,
		 @Param("tipoMedicao") TipoMedicaoModel tipoMedicao);
	
	@Query("select escalonamento from EscalonamentoModel escalonamento "
			+ "where (:id is null or escalonamento.id = :id) "
			+ "and (:tipoMedicaoId is null or escalonamento.tipoMedicao.id = :tipoMedicaoId) "
			+ "and(:faixa is null or escalonamento.faixa = :faixa) "
			+ "and (:desempenho is null or escalonamento.desempenho = :desempenho) "
			+ "order by escalonamento.id asc")
	public List<EscalonamentoModel> findByFilter(
			@Param("id") Long id,
			@Param("tipoMedicaoId") Long tipoMedicaoId,
			@Param("faixa") BigDecimal faixa,
			@Param("desempenho") BigDecimal desempenho);
	
	public Optional<EscalonamentoModel> findById(Long id);

	public Optional<EscalonamentoModel> findByTipoMedicaoId(Long tipoMedicaoId);
}
