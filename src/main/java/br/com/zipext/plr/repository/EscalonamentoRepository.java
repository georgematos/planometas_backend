package br.com.zipext.plr.repository;

import java.math.BigDecimal;
import java.util.List;

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
}
