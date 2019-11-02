package br.com.zipext.plr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.ColaboradorModel;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorModel, String> {

	public ColaboradorModel findByMatricula(String matricula);
}
