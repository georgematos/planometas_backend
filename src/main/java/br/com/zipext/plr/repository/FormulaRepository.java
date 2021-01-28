package br.com.zipext.plr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.FormulaModel;

@Repository
public interface FormulaRepository extends JpaRepository<FormulaModel, Long> {

	public List<FormulaModel> findAllByOrderByNomeAsc();

	@Query(
			"select model from FormulaModel model "
			+ "where model.situacao = :situacao "
			+ "order by model.nome asc")
	public List<FormulaModel> findAllAtivosByOrderByNomeAsc(@Param("situacao") String situacao);

	@Query(
			"select formula from FormulaModel formula "
			+ "where (:id is null or formula.id = :id) "
			+ "and (:nome is null or formula.nome like %:nome%) "
			+ "and (:descricao is null or formula.evalFormula = :descricao) "
			+ "and (:situacao is null or formula.situacao = :situacao) "
			+ "order by formula.nome asc")
	public List<FormulaModel> findByFilter(
			@Param("id") Long id,
			@Param("nome") String nome,
			@Param("descricao") String descricao,
			@Param("situacao") String situacao);

	public Optional<FormulaModel> findByNome(String nome);
}
