package br.com.zipext.plr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.TipoMedicaoModel;

@Repository
public interface TipoMedicaoRepository extends JpaRepository<TipoMedicaoModel, Long> {

	public List<TipoMedicaoModel> findAllByOrderByDescricaoAsc();
	
	@Query("select tipoMedicao from TipoMedicaoModel tipoMedicao "
			+ "where (:id is null or tipoMedicao.id = :id) "
			+ "and (:descricao is null or tipoMedicao.descricao like %:descricao%) "
			+ "order by tipoMedicao.descricao asc")
	public List<TipoMedicaoModel> findByFilter(
			@Param("id") Long id,
			@Param("descricao") String descricao);
	
	public Optional<TipoMedicaoModel> findByDescricao(String descricao);
}
