package br.com.zipext.plr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.MetaEspecificaModel;

@Repository
public interface MetaEspecificaRepository extends JpaRepository<MetaEspecificaModel, Long> {

}
