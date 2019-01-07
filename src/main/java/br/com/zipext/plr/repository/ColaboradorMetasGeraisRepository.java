package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.ColaboradorMetaGeralModel;

@Repository
public interface ColaboradorMetasGeraisRepository extends JpaRepository <ColaboradorMetaGeralModel, ColaboradorMetaGeralModel.ColaboradorMetaGeralModelPK> {

	@Query("select model from ColaboradorMetaGeralModel model"
			+ " join fetch model.pk.colaborador colab"
			+ " where colab.matricula = :matricula" )
	public List<ColaboradorMetaGeralModel> findByMatricula(@Param("matricula") String matricula);
}
