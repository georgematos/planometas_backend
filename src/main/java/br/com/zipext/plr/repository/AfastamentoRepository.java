package br.com.zipext.plr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.AfastamentoModel;
import br.com.zipext.plr.model.ColaboradorModel;

@Repository
public interface AfastamentoRepository extends JpaRepository<AfastamentoModel, AfastamentoModel.AfastamentoPK> {

	@Query("select model from AfastamentoModel model "
		 + "where model.pk.colaborador = :colaborador "
		 + "and model.fimAfastamento is null")
	public AfastamentoModel findAfastamentoAtivoByColaborador(@Param("colaborador") ColaboradorModel colaborador);
}
