package br.com.zipext.plr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.DiretoriaModel;

@Repository
public interface DiretoriaRepository extends JpaRepository<DiretoriaModel, Long> {

	@Query("select diretoria from DiretoriaModel diretoria "
			+ "where (:id is null or diretoria.id = :id) "
			+ "and (:nome is null or diretoria.nome like %:nome%) "
			+ "order by diretoria.nome asc")
	public List<DiretoriaModel> findByFilter(
			@Param("id") Long id,
			@Param("nome") String descricao);

	public List<DiretoriaModel> findAllByOrderByNomeAsc();
	
	public Optional<DiretoriaModel> findByNome(String nome);
}
