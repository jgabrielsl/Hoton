package br.com.hoton.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hoton.UserDetailImp;
import br.com.hoton.models.Usuario;
import br.com.hoton.models.dto.QrCodeResponse;
import br.com.hoton.models.enums.FormaPagamentoEnum;
import br.com.hoton.models.whatsapp.Conf;
import br.com.hoton.models.whatsapp.ContatoStatus;
import br.com.hoton.models.whatsapp.MensagemWhatsapp;
import br.com.hoton.models.whatsapp.MsgWhatsappType;
import br.com.hoton.repository.ConfRepo;
import br.com.hoton.repository.ContatoRepo;
import br.com.hoton.repository.MsgWhatsappRepo;

@Controller
@RequestMapping(value={"/admin/whatsapp/", "/admin/whatsapp"})
public class WhatsappController {
	
	@Autowired
	private MsgWhatsappRepo repoMsg;
	
	@Autowired
	private ConfRepo repo;
	
	@Autowired
	private ContatoRepo cont;
	
	
	@GetMapping(value = {"/", ""})
	public String index(Model model, Authentication authentication) {	
		model.addAttribute("boleto", FormaPagamentoEnum.BOLETO);
		model.addAttribute("pix", FormaPagamentoEnum.PIX);
		model.addAttribute("cartaoCredito", FormaPagamentoEnum.CARTAO_CREDITO);
		return "whatsapp";
	}
	
	@GetMapping(value = {"/content/{tipoVenda}", "/content/{tipoVenda}/"})
	public String content(Model model, Authentication authentication, @PathVariable(required = true) Integer tipoVenda) {	
		List<MsgWhatsappType> lista = MsgWhatsappType.getList();
		
		List<Object[]> types = repoMsg.encontraPorTipoEUsuario(tipoVenda, getUser(authentication).getId());
		
		for (int i = 0; i < types.size(); i++) {
			Long id = ((BigInteger) types.get(i)[0]).longValue();
			types.get(i)[0] = lista.stream().filter(sts -> sts.getId().equals(id)).findFirst().get();
		}
		model.addAttribute("tipoVenda", tipoVenda);
		model.addAttribute("status", types);
		return "components/statusVenda";
	}
	
	@GetMapping(value = {"/edit/{id}/{tipoPagamento}", "edit/{id}/{tipoPagamento}"})
	public String edit(Model model, Authentication authentication, @PathVariable(required = true) Long id, @PathVariable(required = true) Integer tipoPagamento) {	
		try {
			Optional<MensagemWhatsapp> opt = repoMsg.findTopByTypeAndUserAndTipoPagamentoOrderByIdDesc(MsgWhatsappType.getStatus(id), getUser(authentication), tipoPagamento);
			
			if(opt.isPresent()) {
				model.addAttribute("tipoPag", FormaPagamentoEnum.getDesc(tipoPagamento));
				model.addAttribute("msg", opt.get());
			}else
				return "redirect:/admin/whatsapp";
				//model.addAttribute("ERRO", "Mensagem não encontrada.");
		}catch (Exception e) {
			//model.addAttribute("ERRO", "Erro ao recuperar mensagem.");
			e.printStackTrace();
			return "redirect:/admin/whatsapp";
		}
		return "whatsappMensagem";
	}
	
	@PostMapping(value = {"/edit/", "edit"})
	public String editPost(Model model, Authentication authentication, @RequestParam(required = false) Long id,
			@RequestParam(required = false) Long idType, 
			@RequestParam(required = false, defaultValue = "") String mensagem,
			@RequestParam(required = false, defaultValue = "0") Integer dataAfter,
			@RequestParam(required = false, defaultValue = "1") Integer tipoPagamento) {	
		try {
			System.out.println(tipoPagamento);
			MensagemWhatsapp msg;
			Optional<MensagemWhatsapp> opt = repoMsg.findTopByTypeAndUserAndTipoPagamentoOrderByIdDesc(MsgWhatsappType.getStatus(idType), getUser(authentication), tipoPagamento);
			if(opt.isPresent()) {
				msg = opt.get();
				msg.setMensagem(mensagem);
				msg.setDataAfter(dataAfter);
				msg.setTipoPagamento(tipoPagamento);
			}
			else 
				msg = new MensagemWhatsapp(mensagem, true, MsgWhatsappType.getStatus(idType), getUser(authentication), dataAfter, tipoPagamento);
			
			repoMsg.save(msg);
		}catch (Exception e) {
			//model.addAttribute("ERRO", "Erro ao recuperar mensagem.");
			e.printStackTrace();
		}
		model.addAttribute("boleto", FormaPagamentoEnum.BOLETO);
		model.addAttribute("pix", FormaPagamentoEnum.PIX);
		model.addAttribute("cartaoCredito", FormaPagamentoEnum.CARTAO_CREDITO);
		model.addAttribute("last", tipoPagamento);
		return "whatsapp";
	}
	
	@GetMapping(value = {"/habilitar/{id}/{tipoPagamento}", "habilitar/{id}/{tipoPagamento}"})
	public String habilitar(Model model, Authentication authentication, @PathVariable(required = true) Long id,  @PathVariable(required = true) Integer tipoPagamento) {	
		try {
			Optional<MensagemWhatsapp> opt = repoMsg.findTopByTypeAndUserAndTipoPagamentoOrderByIdDesc(MsgWhatsappType.getStatus(id), getUser(authentication), tipoPagamento);
			MensagemWhatsapp msg;
			if(opt.isPresent()) {
				msg = opt.get();
				msg.setAtivo(true);
			}
			else 
				msg = new MensagemWhatsapp("", true, MsgWhatsappType.getStatus(id), getUser(authentication), 0, tipoPagamento);
			
			repoMsg.save(msg);
		}catch (Exception e) {
			model.addAttribute("ERRO", "Erro ao habilitar mensagem.");
			e.printStackTrace();
		}
		
		return "redirect:/admin/whatsapp";
	}
	
	@GetMapping(value = {"/desabilitar/{id}/{tipoPagamento}", "desabilitar/{id}/{tipoPagamento}"})
	public String desabilitar(Model model, Authentication authentication, @PathVariable(required = true) Long id, @PathVariable(required = true) Integer tipoPagamento) {	
		try {
			Optional<MensagemWhatsapp> opt = repoMsg.findTopByTypeAndUserAndTipoPagamentoOrderByIdDesc(MsgWhatsappType.getStatus(id), getUser(authentication), tipoPagamento);
			MensagemWhatsapp msg;
			if(opt.isPresent()) {
				msg = opt.get();
				msg.setAtivo(false);
			}
			else 
				msg = new MensagemWhatsapp("", false, MsgWhatsappType.getStatus(id), getUser(authentication), 0, tipoPagamento);
			
			repoMsg.save(msg);
		}catch (Exception e) {
			model.addAttribute("ERRO", "Erro ao desabilitar mensagem.");
			e.printStackTrace();
		}
		
		return "redirect:/admin/whatsapp";
	}
	
	@GetMapping(value = {"/qrcode", "/qrcode"})
	public String qrcode(Model model, Authentication authentication) {
		try {
			Usuario user = getUser(authentication);
			if(!authentication.getAuthorities().stream().filter(a -> a.getAuthority().equals("ROLE_WHATS")).findAny().isPresent())
				return "redirect:/admin/home";
			
			Conf conf = repo.findByUsuario(user);
			
			if(conf == null) {
				conf = new Conf();
				conf.setChavePrivada("teste");
				conf.setSessao(user.getNome()+user.getId().toString());
				conf.setUsuario(user);
				conf.setStatus("Sem Conexão");
				conf = repo.save(conf);
			}
			
			model.addAttribute("conf", conf);
			
			return "qrcode";
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/whatsapp";
	}
	
	@PostMapping(value = {"/conf/{id}/multDevice/{isMultDevice}/", "/conf/{id}/multDevice/{isMultDevice}"})
	@ResponseBody
	public ResponseEntity<QrCodeResponse> qrcodeget(
			Model model, Authentication authentication, 
			@PathVariable(required = true) Long id, @PathVariable(required = true) boolean isMultDevice) {
		try {
			Usuario user = getUser(authentication);
			if(!authentication.getAuthorities().stream().filter(a -> a.getAuthority().equals("ROLE_WHATS")).findAny().isPresent())
				return new ResponseEntity<QrCodeResponse>(HttpStatus.FAILED_DEPENDENCY);
			
			Conf conf = repo.findByIdAndUsuario(id, user);
			conf.setMultDevice(isMultDevice);
			repo.save(conf);
		   return new ResponseEntity<QrCodeResponse>(HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<QrCodeResponse>(HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	private Usuario getUser(Authentication auth) {
		return ((UserDetailImp) auth.getPrincipal()).getUser();
	}
}
