package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.CargoModel;

@Repository
public interface CargoRepository extends JpaRepository<CargoModel, Long> {

	public List<CargoModel> findAllByOrderByNomeAsc();
	
	@Query("select cargo from CargoModel cargo "
			 + "where (:id is null or cargo.id = :id) "
			 + "and (:nome is null or cargo.nome like %:nome%) "
			 + "and (:equivalencia is null or cargo.equivalencia.id = :equivalencia) "
			 + "and (:situacao is null or cargo.situacao = :situacao) "
			 + "order by cargo.nome asc")
	public List<CargoModel> findByFilter(
			@Param("id") Long id,
			@Param("nome") String nome,
			@Param("equivalencia") Long equivalencia,
			@Param("situacao") String situacao);

	public CargoModel findByNome(String nome);
}
