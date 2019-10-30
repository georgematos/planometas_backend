package br.com.zipext.plr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.FolhaMetaAnualModel;
import br.com.zipext.plr.model.FolhaMetaAnualModel.FolhaMetaAnualPK;
import br.com.zipext.plr.model.MetasModel;

@Repository
public interface FolhaMetaAnualRepository extends JpaRepository<FolhaMetaAnualModel, FolhaMetaAnualPK> {

	@Query("select model from FolhaMetaAnualModel model "
		 + "join fetch model.pk.meta meta "
		 + "join fetch model.pk.tempo tempo "
		 + "where meta = :meta "
		 + "and tempo.ano = :ano")
	public FolhaMetaAnualModel findByMetaAndAno(@Param("meta") MetasModel meta, @Param("ano") Integer ano);
	
}
