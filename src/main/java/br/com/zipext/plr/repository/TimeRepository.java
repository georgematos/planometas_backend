package br.com.zipext.plr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.TimeModel;

@Repository
public interface TimeRepository extends JpaRepository<TimeModel, String> {

	public List<TimeModel> findAllByOrderByNomeAsc();

	@Query("SELECT t FROM TimeModel t "
			+ "WHERE (:codigo IS NULL OR t.codigo = :codigo) "
			+ "AND (:nome IS NULL OR t.nome LIKE %:nome%) "
			+ "ORDER BY t.nome ASC")
	public List<TimeModel> findByFilter(
			@Param("codigo") String codigo, 
			@Param("nome") String nome);

	public Optional<TimeModel> findByNome(String nome);
}
