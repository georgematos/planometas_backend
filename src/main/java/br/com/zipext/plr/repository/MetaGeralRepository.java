package br.com.zipext.plr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.MetaGeralModel;

@Repository
public interface MetaGeralRepository extends JpaRepository<MetaGeralModel, Long> {

}
