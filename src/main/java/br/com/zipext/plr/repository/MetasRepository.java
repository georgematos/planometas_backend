package br.com.zipext.plr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.MetasModel;

@Repository
public interface MetasRepository extends JpaRepository<MetasModel, Long> {

}
