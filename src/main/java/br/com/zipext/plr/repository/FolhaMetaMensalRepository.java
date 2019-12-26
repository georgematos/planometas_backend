package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.FolhaMetaMensalModel;
import br.com.zipext.plr.model.MetasModel;

@Repository
public interface FolhaMetaMensalRepository extends JpaRepository<FolhaMetaMensalModel, Long> {
	
	public Long countByMeta(MetasModel meta);
	
	@Modifying
	public void deleteByMeta(MetasModel meta);

	@Query("select model from FolhaMetaMensalModel model "
	   	+  "join fetch model.meta meta "
	   	+  "join fetch model.prazo prazo "
	   	+  "where meta = :meta "
	   	+  "and prazo.ano = :ano "
	   	+  "order by prazo.mes asc")
	public List<FolhaMetaMensalModel> findByMetaAndAno(@Param("meta") MetasModel meta, @Param("ano") Integer ano);
}
