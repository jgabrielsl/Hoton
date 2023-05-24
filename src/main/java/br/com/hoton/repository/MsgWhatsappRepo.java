package br.com.hoton.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.hoton.models.Usuario;
import br.com.hoton.models.whatsapp.MensagemWhatsapp;
import br.com.hoton.models.whatsapp.MsgWhatsappType;

public interface MsgWhatsappRepo extends CrudRepository<MensagemWhatsapp, Long>{

	Optional<MensagemWhatsapp> findByType(MsgWhatsappType status);

	@Query(nativeQuery = true, value = 
				"SELECT type.id as typeId, msg.id as msgId, msg.ativo as ativoMsg from msg_whatsapp_type type 		"
			+	"LEFT JOIN mensagem_whatsapp msg ON (type.id = msg.type_id and msg.tipo_pagamento = ?1 and msg.usuario_id = ?2)	"
						)
	List<Object[]> encontraPorTipoEUsuario(Integer tipoVenda, Long id);

	Optional<MensagemWhatsapp> findTopByTypeOrderByIdDesc(MsgWhatsappType status);
	
	Optional<MensagemWhatsapp> findTopByTypeAndUserAndTipoPagamentoOrderByIdDesc(MsgWhatsappType status, Usuario user, Integer tipoPagamento);

}
