package br.com.hoton.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.hoton.models.whatsapp.MensagemWhatsErro;

public interface MsgWhatsappErrorRepo extends CrudRepository<MensagemWhatsErro, Long>{

}
