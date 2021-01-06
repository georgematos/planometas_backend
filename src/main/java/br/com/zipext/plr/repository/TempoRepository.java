package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.TempoModel;

@Repository
public interface TempoRepository extends JpaRepository<TempoModel, Long> {

	@Query("select distinct model.ano from TempoModel model "
		 + "order by model.ano desc")
	public List<Integer> findDistinctAno();
}
