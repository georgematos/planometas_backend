package br.com.zipext.plr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.ColaboradorModel;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorModel, String> {

	@Query(
		value = "select model from ColaboradorModel model "
	     + "join fetch model.cargo cargo "
	     + "join fetch model.diretoria diretoria "
	     + "join fetch model.time time "
		 + "where (:matricula is null or model.matricula = :matricula) "
		 + "and (:cpf is null or model.cpf = :cpf) "
		 + "and (:nome is null or model.nome like %:nome%) "
		 + "and (:situacao is null or model.situacao = :situacao) "
		 + "and (:cargo is null or cargo.nome like %:cargo%) "
		 + "and (:diretoria is null or diretoria.nome like %:diretoria%) "
		 + "and (:time is null or time.nome like %:time%) "
		 + "and (:superiorImediato is null or model.superiorImediato.matricula = :superiorImediato) "
		 + "order by model.nome asc",
		 countQuery = "select count(model) from ColaboradorModel model "
		 		+ "join model.cargo cargo "
		 		+ "join model.diretoria diretoria "
		 		+ "join model.time time "
		 		+ "where (:matricula is null or model.matricula = :matricula) "
		 		+ "and (:cpf is null or model.cpf = :cpf) "
		 		+ "and (:nome is null or model.nome like %:nome%) "
		 		+ "and (:situacao is null or model.situacao = :situacao) "
		 		+ "and (:cargo is null or cargo.nome like %:cargo%) "
		 		+ "and (:diretoria is null or diretoria.nome like %:diretoria%) "
		 		+ "and (:time is null or time.nome like %:time%) "
		 		+ "and (:superiorImediato is null or model.superiorImediato.matricula = :superiorImediato) "
		 		+ "group by model.nome "
		 		+ "order by model.nome asc")
	public Page<ColaboradorModel> findByFilter(
			@Param("matricula") String matricula,
			@Param("cpf") String cpf,
			@Param("nome") String nome, 
			@Param("situacao") String situacao,
			@Param("cargo") String cargo,
			@Param("diretoria") String diretoria,
			@Param("time") String time,
			@Param("superiorImediato") String superiorImediato,
			Pageable pageable);
	public ColaboradorModel findByMatricula(String matricula);
}
