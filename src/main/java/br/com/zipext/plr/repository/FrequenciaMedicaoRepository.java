package br.com.zipext.plr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.FrequenciaMedicaoModel;

@Repository
public interface FrequenciaMedicaoRepository extends JpaRepository<FrequenciaMedicaoModel, Long> {

	public List<FrequenciaMedicaoModel> findAllByOrderByDescricaoAsc();

	@Query("select frequenciaMedicao from FrequenciaMedicaoModel frequenciaMedicao "
			+ "where (:id is null or frequenciaMedicao.id = :id) "
			+ "and (:descricao is null or frequenciaMedicao.descricao like %:descricao%) "
			+ "order by frequenciaMedicao.descricao asc")
	public List<FrequenciaMedicaoModel> findByFilter(
			@Param("id") Long id,
			@Param("descricao") String descricao);
	
	public Optional<FrequenciaMedicaoModel> findByDescricao(String descricao);
}
