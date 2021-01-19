package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.EquivalenciaModel;

@Repository
public interface EquivalenciaRepository extends JpaRepository<EquivalenciaModel, Long> {

	public List<EquivalenciaModel> findAllByOrderByDescricaoAsc();
	
//	@Query("select equivalencia from EquivalenciaModel equivalencia "
//			 + "where (:id is null or cargo.id = :id) "
//			 + "and (:nome is null or cargo.nome like %:nome%) "
//			 + "and (:equivalencia is null or cargo.equivalencia.id = :equivalencia) "
//			 + "order by cargo.nome asc")
//	public List<CargoModel> findByFilter(
//			@Param("id") Long id,
//			@Param("nome") String nome,
//			@Param("equivalencia") Long equivalencia);
}
