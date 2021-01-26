package br.com.zipext.plr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.TipoMetaModel;

@Repository
public interface TipoMetaRepository extends JpaRepository<TipoMetaModel, Long> {
	
	@Query("select tipoMeta from TipoMetaModel tipoMeta "
			+ "where (:id is null or tipoMeta.id = :id) "
			+ "and (:descricao is null or tipoMeta.descricao like %:descricao%) "
			+ "and (:abreviacao is null or tipoMeta.abreviacao = :abreviacao) "
			+ "and (:restrita is null or tipoMeta.isMetaRestrita = :restrita) "
			+ "order by tipoMeta.descricao asc")
	public List<TipoMetaModel> findByFilter(
			@Param("id") Long id,
			@Param("descricao") String descricao,
			@Param("abreviacao") String abreviacao,
			@Param("restrita") String restrita);

	public List<TipoMetaModel> findAllByOrderByDescricaoAsc();
	
	public Optional<TipoMetaModel> findByDescricao(String descricao);

	public Optional<TipoMetaModel> findByAbreviacao(String abreviacao);
}
