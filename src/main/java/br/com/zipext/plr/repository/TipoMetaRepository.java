package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.TipoMetaModel;

@Repository
public interface TipoMetaRepository extends JpaRepository<TipoMetaModel, Long> {

	public List<TipoMetaModel> findAllByOrderByDescricaoAsc();
}
