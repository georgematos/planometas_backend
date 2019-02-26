package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.HistoricoModel;
import br.com.zipext.plr.model.UsuarioModel;

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoModel, Long> {

	long countByColaborador(ColaboradorModel colaborador);
	
	public List<HistoricoModel> findByColaborador(ColaboradorModel colaborador);
	
	public List<HistoricoModel> findByResponsavel(UsuarioModel responsavel);
}
