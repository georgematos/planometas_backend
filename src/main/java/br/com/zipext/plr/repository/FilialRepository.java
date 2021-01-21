package br.com.zipext.plr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.FilialModel;

@Repository
public interface FilialRepository extends JpaRepository<FilialModel, Long> {

	public List<FilialModel> findAllByOrderByNomeAsc();
	
	@Query("select filial from FilialModel filial "
			+ "where (:id is null or filial.id = :id) "
			+ "and (:nome is null or filial.nome like %:nome%) "
			+ "order by filial.nome asc")
	public List<FilialModel> findByFilter(
			@Param("id") Long id,
			@Param("nome") String nome);
	
	public Optional<FilialModel> findByNome(String nome);
}
