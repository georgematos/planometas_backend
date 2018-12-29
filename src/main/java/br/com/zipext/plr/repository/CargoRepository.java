package br.com.zipext.plr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zipext.plr.model.CargoModel;

@Repository
public interface CargoRepository extends JpaRepository<CargoModel, Long> {

}
