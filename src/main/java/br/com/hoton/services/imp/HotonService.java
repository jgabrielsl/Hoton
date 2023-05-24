package br.com.hoton.services.imp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.hoton.models.Role;
import br.com.hoton.models.Usuario;
import br.com.hoton.models.Venda;
import br.com.hoton.models.enums.StatusVendaEnum;
import br.com.hoton.models.whatsapp.Conf;
import br.com.hoton.models.whatsapp.Contato;
import br.com.hoton.models.whatsapp.ContatoStatus;
import br.com.hoton.models.whatsapp.MensagemWhatsapp;
import br.com.hoton.models.whatsapp.MsgWhatsappType;
import br.com.hoton.repository.ConfRepo;
import br.com.hoton.repository.ContatoRepo;
import br.com.hoton.repository.MsgWhatsappRepo;
import br.com.hoton.repository.RoleRepository;
import br.com.hoton.repository.UsuarioRepository;
import br.com.hoton.repository.VendaRepository;
import br.com.hoton.rest.domains.HotmartJson;

@Service
public class HotonService {

	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private VendaRepository vendaRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ConfRepo repoConf;
	
	@Autowired
	private ContatoRepo repoContato;
	
	@Autowired
	private MsgWhatsappRepo repoMsg;
	
	@Autowired
	private EncryptService enc;
	
	@Autowired
	private EmailServiceImp email;
	
	private static final Logger logger = LogManager.getLogger(HotonService.class);
	
	public boolean processaStatusCompraQuery(Venda venda, String hotonTok, String hottok) throws Exception {
		
		Usuario user; 
		
		Usuario adminReceived = userRepo.findByHotonTokAndHotmartToken(hotonTok, hottok);
		Usuario admin = userRepo.findByEmailIgnoreCase("JONATANDRUMOND@YAHOO.COM.BR");
		
		logger.info("ID : " + venda.getVendaId() +" ENTROU");
		
		if(venda.isTest()) {
			user = userRepo.findByHotonTok(hotonTok);
			logger.info("VENDA RECEBIDA ->  -> ID : " + venda.getVendaId());
			logger.info("ID : " + venda.getVendaId() +" TESTE");
		}else
			user = userRepo.findByEmail(venda.getEmail());
		
		logger.info("ID : " + venda.getVendaId() +"; USUARIO : "+(user==null?"NULL":user.getId()));
		
		try {
			
			if(user == null) {
			    user = criaUsuario(venda);
			    logger.info("ID : " + venda.getVendaId() +"; USUARIO : "+user.getId()+" CRIADO");
			}
			if(user.getEmail().toUpperCase().equals(admin.getEmail().toUpperCase())) {
				sendEmailFraudeEmail(user, venda);
				logger.info("ID : " + venda.getVendaId() +"; USUARIO : "+user.getId()+" FRAUDE EMAIL");
				throw new Exception("Email invalido");
			}
			user = userRepo.save(user);
			
			if(venda.getVendaStatusId() == StatusVendaEnum.FINALIZADA.getId()) {
				logger.info("ID : " + venda.getVendaId() +"; USUARIO : "+user.getId()+" INICIOU ENVIO SENHA");
				String senha = generateKey();
				
				user.setCodAssinante(venda.getCodInscrito());
				user.setPassword(passwordEncoder.encode(senha));
				sendEmailNewUser(user, venda.isTest(), senha);
				
				user.setEnabled(true);
				
				user.setHotonTok(enc.simpleEncrypt(user.getEmail(), user.getNome()));
				user = userRepo.save(user);
				
				sendEmailAdmin(admin, venda);
				logger.info("ID : " + venda.getVendaId() +"; USUARIO : "+user.getId()+" FINALIZOU ENVIO SENHA");
			}
			
			if(venda.getContatoStatusId() == 4 || venda.getContatoStatusId() == 8) {
				user.setEnabled(false);
				user.setCancelamentoServico(true);
				user = userRepo.save(user);
				sendEmailReembolso(admin,venda);
				logger.info("ID : " + venda.getVendaId() +"; USUARIO : "+user.getId()+" REEMBOLSO SOLICITADO");
			}
			
			if(venda.getContatoStatusId() == 3) {
				user.setEnabled(false);
				user.setCancelamentoServico(true);
				user = userRepo.save(user);
				sendEmailCancelamento(admin, venda);
				logger.info("ID : " + venda.getVendaId() +"; USUARIO : "+user.getId()+" CANCELAMENTO");
			}
			
			venda.setUsuario(admin);
			venda = vendaRepo.save(venda);
			
			List<Optional<Venda>> oldTransactions = vendaRepo.findByVendaIdAndNextIdIsNullAndIdNotOrderByIdDesc(venda.getVendaId(), venda.getId());
			if(oldTransactions != null && oldTransactions.size() > 0 && oldTransactions.get(0).isPresent()) {
				oldTransactions.get(0).get().setNextId(venda.getId());
				vendaRepo.save(oldTransactions.get(0).get());
			}
			
			if(adminReceived == null || !adminReceived.getEmail().toUpperCase().equals(admin.getEmail().toUpperCase())) {
				venda.setAvaliacaoVenda("POSSIVEL_FRAUDE");
				user.setEnabled(false);
				user = userRepo.save(user);
				sendEmailFraudeToken(admin, venda);
				logger.info("ID : " + venda.getVendaId() +"; USUARIO : "+user.getId()+" FRAUDE TOKEN");
			}else {
				venda.setAvaliacaoVenda("OK");
				logger.info("ID : " + venda.getVendaId() +"; USUARIO : "+user.getId()+" OK");
				
				if(venda.getVendaStatusId() == StatusVendaEnum.AGUARDANDO_PAGAMENTO.getId())
					sendEmailAdminLead(admin, venda);
			}
		}catch (Exception e) {
			throw e;
		}
		
		try {
			Conf conf = repoConf.findByUsuario(userRepo.findById(1l).get());
			Optional<MensagemWhatsapp> whats = repoMsg.findTopByTypeAndUserAndTipoPagamentoOrderByIdDesc(MsgWhatsappType.getStatus(venda.getVendaStatusId().longValue()), venda.getUsuario(), venda.getFormaPagamentosId());
			if(whats.isPresent() && whats.get().isAtivo()) {
				Contato contato = new Contato();
				contato.setConf(conf);
				contato.setMensagem(whats.get());
				contato.setTimes(LocalDateTime.now());
				contato.setStatus(ContatoStatus.AGUARDANDO_ENVIO);
				contato.setTelefone("55"+venda.getUsuario().getTelefone());
				repoContato.save(contato);
			}
		}catch (Exception e) {
			System.out.println("ERRO"+e);
		}
		
		return true;
	}
	
	private void sendEmailCancelamento(Usuario admin, Venda venda) {
		sendEmail(admin.getEmail(),
				"PEDIDO CANCELADA", 
				"Olá,\n\nRecebemos um pedido de cancelamento da venda:"+venda.getVendaId()+", cliente:"+venda.getNome()+"."+(venda.isTest()?"\n\nÉ UM TESTE!":""));
	}

	private void sendEmailReembolso(Usuario admin, Venda venda) {
		sendEmail(admin.getEmail(),
				"REEMBOLSO EFETUADO", 
				"Olá,\n\nA Venda:"+venda.getVendaId()+", cliente:"+venda.getNome()+", usuario: "+venda.getEmail()+" foi reembolsada com sucesso!"+(venda.isTest()?"\n\nÉ UM TESTE!":""));
	}

	private void sendEmailFraudeToken(Usuario admin, Venda venda) {
		sendEmail(admin.getEmail(), "FRAUDE TOKEN", 
				"Olá,\n\nRecebemos um status de venda da Hotmart que possilvelmente se trata de uma fraude.\n\nMotivo: Token único recebido do Hotmart não confere com os dados do adminstrador do sistema."
				+"\n\nVenda:"+venda.getVendaId()+", cliente:"+venda.getNome()+(venda.isTest()?"\n\nÉ UM TESTE!":""));
	}

	private void sendEmailFraudeEmail(Usuario user, Venda venda) {
		sendEmail(user.getEmail(), "FRAUDE EMAIL", 
				"Olá,\nRecebemos um status de venda da Hotmart que possilvelmente se trata de uma fraude.\nMotivo: O Email recebido da compra é o mesmo do administrador do sistema."
				+"\n\nVenda:"+venda.getVendaId()+", cliente:"+venda.getNome()+(venda.isTest()?"\n\nÉ UM TESTE!":""));
	}
	
	private void sendEmailNewUser(Usuario user, boolean isTest, String senha) {
		//sendEmail(user.getEmail(), "BEM VINDO A HOT ON!", 
		//		"Olá,\n\nRecebemos sua assinatura de nossos serviços.\n\nAbaixo terá as informações necessarias para efetuar o primeiro uso do sistema:"
		//		+"\n\nUsuario: "+user.getEmail()+"\n\nSenha: "+senha+(isTest?"\n\nÉ UM TESTE!":""));
		sendEmail(user.getEmail(),"BEM VINDO A HOT ON!","Seu pedido foi confirmado com sucesso.\n\n"+
		"Parabéns, você acaba de dar um passo muito importante para alavancar suas vendas.\n\n"+
		"Você acaba de receber o acesso para entrar no time HotOn!\n\n"+
		"No link abaixo, você encontrará mais detalhes da sua jornada.\n\n"+
		"Em caso de dúvidas, fique à vontade para nos contatar através do hotonoficial@gmail.com\n\n\n"+
		"🚀\n\n"+
		"Nome de usuário:"+user.getEmail()+"\n"+
		"Senha:"+senha+"\n"+
		"Link de Acesso a HotOn: https://hoton.com.br/login\n\n\n"+
		"Abraços,\n"+
		"Equipe HotOn");
	}

	private void sendEmailAdmin(Usuario admin, Venda venda) {
		sendEmail(admin.getEmail(), "NOVO USUARIO", 
				"Olá,\nRecebemos o cadastro de um novo usuario."
				+"\n\nVenda:"+venda.getVendaId()
				+"\n\ncliente:"+venda.getNome()
				+"\n\nEmail: "+venda.getEmail()
				+(venda.isTest()?"\n\nÉ UM TESTE!":""));
	}
	
	private void sendEmailAdminLead(Usuario admin, Venda venda) {
		sendEmail(admin.getEmail(), "POSSIVEL NOVO USUARIO ", 
				"Olá,\nRecebemos a iniciação de compra de um novo usuario."
				+"\nVamos converter esse boleto!"
				+"\n\nVenda:"+venda.getVendaId()
				+"\n\ncliente:"+venda.getNome()
				+"\n\nEmail: "+venda.getEmail()
				+(venda.isTest()?"\n\nÉ UM TESTE!":""));
	}

	private void sendEmail(String to, String subject, String body) {
		email.enviarEmailSimples(to, subject, body);
	}

	private String generateKey() {
		Random random = new Random();

	    String generatedString = random.ints(97, 122 + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(5)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
	    return generatedString;
	}
	
	public boolean processaStatusCompraJson(HotmartJson domain) throws Exception {
		Usuario user;
		boolean isTest = false;
		
		Usuario adminReceived = userRepo.findByHotonTokAndHotmartToken(domain.getHoton_tok(), domain.getHottok());
		Usuario admin = userRepo.findByEmailIgnoreCase("JONATANDRUMOND@YAHOO.COM.BR");
		
		if(domain.getHottok().equals("v40uKFtBshunmsX3izDQncdt4Xd77n1b132642-6ddb-4533-8c1b-4c73e698335e")) {
			adminReceived = userRepo.findByHotonTok(domain.getHoton_tok());
			isTest = true;
		}
		
		user = userRepo.findByEmail(domain.getUserEmail());
		
		if(user == null)
			user = userRepo.findByCodAssinante(domain.getSubscriberCode());
		
		if(user == null) {
			sendEmail(admin.getEmail(), "USUARIO NÃO ENCONTRADO - CANCELAMENTO DE ASSINATURA", "Usuario não encontrado na base de dados.\n\nEmail recebido: "+domain.getUserEmail()+", Nome: "+domain.getUserName()+(isTest?"\n\nÉ UM TESTE!":""));
			throw new Exception("Usuario não encontrado na base de dados");
		}
		
		if(adminReceived == null || !adminReceived.getEmail().toUpperCase().equals(admin.getEmail().toUpperCase())) {
			sendEmail(admin.getEmail(), "FRAUDE TOKEN", 
					"Olá,\n\nRecebemos um cancelamento da Hotmart que possilvelmente se trata de uma fraude.\n\nMotivo: Token único recebido do Hotmart não confere com os dados do adminstrador do sistema."
					+"\n\nEmail: "+domain.getUserEmail()+", cliente:"+domain.getUserName()+(isTest?"\n\nÉ UM TESTE!":""));
		}
		if(user.getEmail().toUpperCase().equals(admin.getEmail().toUpperCase())) {
			sendEmail(admin.getEmail(), "FRAUDE EMAIL", 
					"Olá,\nRecebemos um cancelamento de assinatura da Hotmart que possilvelmente se trata de uma fraude.\nMotivo: O Email recebido da compra é o mesmo do administrador do sistema."
					+"\n\nEmail: "+domain.getUserEmail()+", cliente:"+domain.getUserName()+(isTest?"\n\nÉ UM TESTE!":""));
		}
		
		user.setCancelamentoServico(true);
		user.setEnabled(false);
		
		userRepo.save(user);
		
		sendEmail(admin.getEmail(), "CANCELAMENTO DE ASSINATURA", 
				"Olá,\nRecebemos um cancelamento de assinatura da Hotmart."
				+"\n\nEmail:"+domain.getUserEmail()+", cliente:"+domain.getUserName()+(isTest?"\n\nÉ UM TESTE!":""));
		
		return true;
	}
	
	public Usuario criaUsuario(Venda venda) {
		Set<Role> roles = new HashSet<Role>();
	    roles.add(roleRepo.getRoleByName("USER"));
	    roles.add(roleRepo.getRoleByName("WHATS"));
		
		Usuario user = new Usuario();
		
		user.setEmail(venda.getEmail().toUpperCase());
		user.setEnabled(false);
		user.setNome(venda.getNome());
		user.setRoles(roles);
		user.setFirstAcess(true);
		user.setSetup(false);
		user.setTelefone(venda.getDdd()+venda.getTelefone());
		
		return user;
	}
}
