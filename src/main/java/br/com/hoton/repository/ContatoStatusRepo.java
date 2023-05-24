package br.com.hoton.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.hoton.models.whatsapp.ContatoStatus;

public interface ContatoStatusRepo extends CrudRepository<ContatoStatus, Long>{

}
