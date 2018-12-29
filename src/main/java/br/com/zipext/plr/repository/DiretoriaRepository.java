package br.com.zipext.plr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.DiretoriaModel;

@Repository
public interface DiretoriaRepository extends JpaRepository<DiretoriaModel, Long> {

}
