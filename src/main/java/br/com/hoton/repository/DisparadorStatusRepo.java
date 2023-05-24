package br.com.hoton.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.hoton.models.enums.DisparadorStatus;

public interface DisparadorStatusRepo extends CrudRepository<DisparadorStatus, Long>{

}
