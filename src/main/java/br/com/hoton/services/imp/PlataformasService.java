package br.com.hoton.services.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hoton.exceptions.TokenUsuarioException;
import br.com.hoton.models.Usuario;
import br.com.hoton.models.Venda;
import br.com.hoton.models.whatsapp.Conf;
import br.com.hoton.models.whatsapp.Contato;
import br.com.hoton.models.whatsapp.ContatoStatus;
import br.com.hoton.models.whatsapp.MensagemWhatsErro;
import br.com.hoton.models.whatsapp.MensagemWhatsapp;
import br.com.hoton.models.whatsapp.MsgWhatsappType;
import br.com.hoton.repository.ConfRepo;
import br.com.hoton.repository.ContatoRepo;
import br.com.hoton.repository.MsgWhatsappErrorRepo;
import br.com.hoton.repository.MsgWhatsappRepo;
import br.com.hoton.repository.UsuarioRepository;
import br.com.hoton.repository.VendaRepository;

@Service
public class PlataformasService {
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private VendaRepository vendaRepo;
	
	@Autowired
	private ConfRepo repoConf;
	
	@Autowired
	private ContatoRepo repoContato;
	
	@Autowired
	private MsgWhatsappRepo repoMsg;
	
	@Autowired
	private MsgWhatsappErrorRepo repoMsgError;

	public boolean processaStatusCompraQuery(Venda venda, String hotonTok, String platToken) throws Exception {

		Usuario user; 
		
		if(platToken.equals("")) {
			user = userRepo.findByHotonTok(hotonTok);
			venda.setTest(true);
		}else {
			venda.setTest(false);
			switch (venda.getPlataforma()) {
			case "HOTMART":
				user = userRepo.findByHotonTokAndHotmartToken(hotonTok, platToken);
				break;
			case "MONETIZZE":
				user = userRepo.findByHotonTokAndMonetizzeToken(hotonTok, platToken);
				break;
			case "EDUZZ":
				user = userRepo.findByHotonTokAndEduzzToken(hotonTok, platToken);
				break;
			default:
				throw new TokenUsuarioException("Plataforma e Token não condizentes", "PLATAFORMA_NAO_ENCONTRADO");
			}
		}
		if(user == null)
			throw new TokenUsuarioException("Tokens enviados não encontrados na base de dados", "USUARIO_NAO_ENCONTRADO");

		
		venda.setUsuario(user);
		venda = vendaRepo.save(venda);
		
		List<Optional<Venda>> oldTransactions = vendaRepo.findByVendaIdAndNextIdIsNullAndIdNotOrderByIdDesc(venda.getVendaId(), venda.getId());
		if(oldTransactions != null && oldTransactions.size() > 0 && oldTransactions.get(0).isPresent()) {
			oldTransactions.get(0).get().setNextId(venda.getId());
			vendaRepo.save(oldTransactions.get(0).get());
		}
		
		try {
			Conf conf = repoConf.findByUsuario(venda.getUsuario());
			if(conf == null) {
				System.out.println("Contato applying-> Conf not find for userId:"+venda.getId());
				return true;
			}
			Integer formPag = venda.getFormaPagamentosId();
			if(venda.getFormaPagamentosId() == null || (venda.getFormaPagamentosId() != 1 && venda.getFormaPagamentosId() != 9 && venda.getFormaPagamentosId() != 2))
				formPag = 2;
			
			System.out.println("Contato applying-> idconf:"+conf.getId());
			Optional<MensagemWhatsapp> whats = repoMsg.findTopByTypeAndUserAndTipoPagamentoOrderByIdDesc(MsgWhatsappType.getStatus(venda.getVendaStatusId().longValue()), venda.getUsuario(), formPag);
			System.out.println("Contato applying-> vendaStatusId:"+venda.getVendaStatusId().longValue()+"; userId:"+venda.getUsuario().getId()+";isMensagem:"+whats.isPresent());
			if((venda.getDdd()+venda.getTelefone()).length() < 10) {
				MensagemWhatsErro erro = new MensagemWhatsErro(venda.getVendaId(), MsgWhatsappType.getStatus(venda.getVendaStatusId().longValue()).getDescricao(), "Venda não tem número de contato válido!", venda.getUsuario());
				repoMsgError.save(erro);
				return true;
			}
			if(whats.isPresent() && whats.get().isAtivo() && !whats.get().getMensagem().trim().isEmpty()) {
				System.out.println("Contato applying-> Mensagem Encontrada!");
				Contato contato = new Contato();
				contato.setConf(conf);
				contato.setMensagem(whats.get());
				contato.setMensagemSend(setaMensagem(whats.get().getMensagem(), venda));
				contato.setTimes(LocalDateTime.now().plusMinutes(whats.get().getDataAfter()));
				contato.setStatus(ContatoStatus.AGUARDANDO_ENVIO);
				//Alterar para telefone do cliente
				contato.setTelefone("55"+venda.getDdd()+venda.getTelefone());
				contato.setVenda(venda);
				contato = repoContato.save(contato);
				System.out.println("Contato applying-> Mensagem Salva! Id:"+contato.getId());
				cancelaContatoRecursivo(venda.getId());
				cancelaContatoTelefoneVendaRecursivo(venda.getDdd(), venda.getTelefone(), venda.getCodProdu(), venda.getFormaPagamentosId(), contato.getId());
			}else {
				MensagemWhatsErro erro = new MensagemWhatsErro(venda.getVendaId(), MsgWhatsappType.getStatus(venda.getVendaStatusId().longValue()).getDescricao(), "Mensagem não configurada ou inativa", venda.getUsuario());
				repoMsgError.save(erro);
			}
		}catch (Exception e) {
			System.out.println("ERRO"+e);
			try {
				String type;
				try {
					type = MsgWhatsappType.getStatus(venda.getVendaStatusId().longValue()).getDescricao();
				}catch (Exception ex) {
					type = "Status de venda não encontrado, id recebido:"+venda.getVendaStatusId();
				}
				MensagemWhatsErro erro = new MensagemWhatsErro(venda.getVendaId(), type, "Erro desconhecido", venda.getUsuario());
				repoMsgError.save(erro);
			}catch (Exception exe) {
				System.out.println("ERRO MENSAGEM DESCONHECIDO");
				exe.printStackTrace();
			}
		}

		return true;
	}

	private String setaMensagem(String mensagem, Venda venda) {
		if(mensagem.indexOf("{{")<0)
			return mensagem;
		String msg = mensagem;
		System.out.println("INICIO"+mensagem);
		try {
			if(msg.indexOf("{{nome_do_produto}}") >= 0)
				if(venda.getProduto() != null)
					msg = msg.replaceAll("\\{\\{nome_do_produto\\}\\}", venda.getProduto());
				else
					msg = msg.replaceAll("\\{\\{nome_do_produto\\}\\}", "");
			if(msg.indexOf("{{id_da_compra}}") >= 0)
				if(venda.getVendaId() != null)
					msg = msg.replaceAll("\\{\\{id_da_compra\\}\\}", venda.getVendaId());
				else
					msg = msg.replaceAll("\\{\\{id_da_compra\\}\\}", "");
			if(msg.indexOf("{{forma_de_pagamento}}") >= 0)
				if(venda.getFormaPagamento() != null)
					msg = msg.replaceAll("\\{\\{forma_de_pagamento\\}\\}", venda.getFormaPagamento());
				else
					msg = msg.replaceAll("\\{\\{forma_de_pagamento\\}\\}", "");
			if(msg.indexOf("{{status_da_compra}}") >= 0)
				if(venda.getVendaStatus()!=null)
					msg = msg.replaceAll("\\{\\{status_da_compra\\}\\}", venda.getVendaStatus());
				else
					msg = msg.replaceAll("\\{\\{status_da_compra\\}\\}", "");
			if(msg.indexOf("{{valor_total}}") >= 0)
				if(venda.getValorTotal() != null)
					msg = msg.replaceAll("\\{\\{valor_total\\}\\}", venda.getValorTotal().toString());
				else
					msg = msg.replaceAll("\\{\\{valor_total\\}\\}", "");
			if(msg.indexOf("{{link_do_boleto}}") >= 0)
				if(venda.getLinkCodigoBarra() != null)
					msg = msg.replaceAll("\\{\\{link_do_boleto\\}\\}", venda.getLinkCodigoBarra());
				else
					msg = msg.replaceAll("\\{\\{link_do_boleto\\}\\}", "");
			if(msg.indexOf("{{linha_digitavel}}") >= 0)
				if(venda.getCodigoBarra() != null)
					msg = msg.replaceAll("\\{\\{linha_digitavel\\}\\}", venda.getCodigoBarra());
				else
					msg = msg.replaceAll("\\{\\{linha_digitavel\\}\\}", "");
			if(msg.indexOf("{{nome_do_comprador}}") >= 0)
				if(venda.getNome() != null)
					msg = msg.replaceAll("\\{\\{nome_do_comprador\\}\\}", venda.getNome());
				else
					msg = msg.replaceAll("\\{\\{nome_do_comprador\\}\\}", "");
			if(msg.indexOf("{{telefone_comprador}}") >= 0)
				if(venda.getTelefone() != null)
					msg = msg.replaceAll("\\{\\{telefone_comprador\\}\\}", venda.getTelefone());
				else
					msg = msg.replaceAll("\\{\\{telefone_comprador\\}\\}", "");
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("FIM"+msg);
		return msg;
	}

	private void cancelaContatoRecursivo(Long id) {
		Optional<Venda> ven = vendaRepo.findByNextId(id);
		if(ven.isPresent()) {
			Optional<Contato> cont = repoContato.findByVenda(ven.get());
			if(cont.isPresent() && (cont.get().getStatus().getId() == ContatoStatus.AGUARDANDO_ENVIO.getId()
					|| cont.get().getStatus().getId() == ContatoStatus.FALHA.getId())) {
				cont.get().setStatus(ContatoStatus.CANCELADO);
				repoContato.save(cont.get());
				MensagemWhatsErro erro = new MensagemWhatsErro(ven.get().getVendaId(), MsgWhatsappType.getStatus(ven.get().getVendaStatusId().longValue()).getDescricao(), "Já há um agendaento para esta venda", ven.get().getUsuario());
				repoMsgError.save(erro);
				cancelaContatoRecursivo(ven.get().getId());
			}else
				return;
		}else
			return;
		
	}
	
	private void cancelaContatoTelefoneVendaRecursivo(String ddd, String telefone, String codProduto, Integer formaPagId,Long id) {
		Optional<Venda> ven = vendaRepo.findByDddAndTelefoneAndCodProduAndFormaPagamentosIdAndDateGreaterThanEqual(
						ddd, telefone, codProduto, formaPagId, 
						LocalDate.now().minusDays(1)
		);
		if(ven.isPresent()) {
			Optional<List<Contato>> cont = repoContato.findAllByVenda(ven.get());
			if(cont.isPresent()) {
				cont.get().forEach(contato ->{
					if(contato.getId() != id && (contato.getStatus().getId() == ContatoStatus.AGUARDANDO_ENVIO.getId()
							|| contato.getStatus().getId() == ContatoStatus.FALHA.getId())) {
						contato.setStatus(ContatoStatus.CANCELADO);
						repoContato.save(contato);
						MensagemWhatsErro erro = new MensagemWhatsErro(ven.get().getVendaId(), MsgWhatsappType.getStatus(ven.get().getVendaStatusId().longValue()).getDescricao(), "Já há um agendaento para este número.", ven.get().getUsuario());
						repoMsgError.save(erro);
					}
				});	
			}
		}else
			return;
		
	}
}
