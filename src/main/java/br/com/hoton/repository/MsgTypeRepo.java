package br.com.hoton.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.hoton.models.whatsapp.MsgWhatsappType;

public interface MsgTypeRepo extends CrudRepository<MsgWhatsappType, Long>{

}
