package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FolhaMetaModel;

@Repository
public interface FolhaMetaRepository extends JpaRepository<FolhaMetaModel, Long> {
	
	public List<FolhaMetaModel> findByColaborador(ColaboradorModel colaborador);

}
