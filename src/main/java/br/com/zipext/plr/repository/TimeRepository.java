package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.TimeModel;

@Repository
public interface TimeRepository extends JpaRepository<TimeModel, Long> {

	public List<TimeModel> findAllByOrderByNomeAsc();
}
