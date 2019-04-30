package br.com.zipext.plr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.HistoricoMetaEspecificaModel;

@Repository
public interface HistoricoMetaEspecificaRepository extends JpaRepository<HistoricoMetaEspecificaModel, HistoricoMetaEspecificaModel.HistoricoMetaEspecificaPK> {

	@Modifying
	@Transactional
	@Query("delete from HistoricoMetaEspecificaModel model"
			+ " where model.pk.historico.id= ?1")
	void deleteByIdHistorico(Long idHistorico);
}
