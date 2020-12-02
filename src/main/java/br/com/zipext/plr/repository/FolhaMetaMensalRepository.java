package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FolhaMetaMensalModel;
import br.com.zipext.plr.model.MetasModel;

@Repository
public interface FolhaMetaMensalRepository extends JpaRepository<FolhaMetaMensalModel, Long> {
	
	public Long countByMeta(MetasModel meta);
	
	@Modifying
	public void deleteByMeta(MetasModel meta);
	
	@Modifying
	@Query("delete from FolhaMetaMensalModel model "
		 + "where model.meta = :meta "
		 + "and   model.colaboradorMeta = :colaborador "
		 + "and   model.prazo in (select tempo from TempoModel tempo where tempo.ano = :ano)")
	public void deleteByMetaColaboradorAndAno(@Param("meta") MetasModel meta, @Param("colaborador") ColaboradorModel colaborador,@Param("ano") Integer ano);

	@Query("select model from FolhaMetaMensalModel model "
	   	+  "join fetch model.meta meta "
	   	+  "join fetch model.colaboradorMeta colaborador "
	   	+  "join fetch model.prazo prazo "
	   	+  "where meta = :meta "
	   	+  "and colaborador = :colaborador "
	   	+  "and prazo.ano = :ano "
	   	+  "order by prazo.mes asc")
	public List<FolhaMetaMensalModel> findByMetaColaboradorAndAno(@Param("meta") MetasModel meta, 
															@Param("colaborador") ColaboradorModel colaborador, 
															@Param("ano") Integer ano);
	
	@Query("select model from FolhaMetaMensalModel model "
		   	+  "join fetch model.meta meta "
		   	+  "join fetch model.prazo prazo "
		   	+  "where meta = :meta "
		   	+  "and prazo.ano = :ano "
		   	+  "order by prazo.mes asc")
		public List<FolhaMetaMensalModel> findByMetaAndAno(@Param("meta") MetasModel meta, 
																@Param("ano") Integer ano);
}
