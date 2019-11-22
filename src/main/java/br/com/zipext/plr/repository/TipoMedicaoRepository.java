package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.TipoMedicaoModel;

@Repository
public interface TipoMedicaoRepository extends JpaRepository<TipoMedicaoModel, Long> {

	public List<TipoMedicaoModel> findAllByOrderByDescricaoAsc();
}
