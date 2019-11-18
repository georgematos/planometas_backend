package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.ColaboradorModel;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorModel, String> {

	@Query("select model from ColaboradorModel model "
	     + "join fetch model.cargo cargo "
	     + "join fetch model.diretoria diretoria "
	     + "join fetch model.time time "
		 + "where (:matricula is null or model.matricula = :matricula) "
		 + "and (:situacao is null or model.situacao = :situacao) "
		 + "and (:nome is null or model.nome like %:nome%) "
		 + "and (:cargo is null or cargo.nome like %:cargo%) "
		 + "and (:diretoria is null or diretoria.nome like %:diretoria%) "
		 + "and (:time is null or time.nome like %:time%) "
		 + "order by model.nome asc")
	public List<ColaboradorModel> findByFilter(@Param("matricula") String matricula, 
			@Param("nome") String nome, 
			@Param("situacao") String situacao,
			@Param("cargo") String cargo,
			@Param("diretoria") String diretoria, 
			@Param("time") String time);
	
	public ColaboradorModel findByMatricula(String matricula);
}
