package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.FormulaModel;

@Repository
public interface FormulaRepository extends JpaRepository<FormulaModel, Long> {

	public List<FormulaModel> findAllByOrderByNomeAsc();
	
	@Query("select model from FormulaModel model "
		+  "where model.situacao = :situacao "
		+  "order by model.nome asc")
	public List<FormulaModel> findAllAtivosByOrderByNomeAsc(@Param("situacao") String situacao);
}


