package br.com.zipext.plr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.FilialModel;

@Repository
public interface FilialRepository extends JpaRepository<FilialModel, Long> {

	public List<FilialModel> findAllByOrderByNomeAsc();
}
