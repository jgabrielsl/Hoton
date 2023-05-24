package br.com.hoton.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.hoton.UserDetailImp;
import br.com.hoton.models.Mensagem;
import br.com.hoton.models.Usuario;
import br.com.hoton.models.Venda;
import br.com.hoton.models.dto.MensagemDTO;
import br.com.hoton.models.dto.VendaDTO;
import br.com.hoton.models.enums.FormaPagamentoEnum;
import br.com.hoton.models.enums.StatusContatoEnum;
import br.com.hoton.models.enums.StatusVendaEnum;
import br.com.hoton.repository.MensagemRepository;
import br.com.hoton.repository.VendaRepository;
import br.com.hoton.services.imp.AcoesServiceImp;

@Controller
@RequestMapping(value = {"/admin/boleto", "/admin/boleto/"})
@SessionAttributes("vendasList")
public class BoletoController {
	
	@Autowired
	private VendaRepository repo;
	
	@Autowired
	private MensagemRepository msgRepo;

	@Autowired
	private AcoesServiceImp servAct;
	
	@GetMapping(value = {"/", ""})
	public String index(Model model, Authentication authentication, 
			@RequestParam(required = false, defaultValue = "1") Integer stsContato, 
			@RequestParam(required = false, defaultValue = "") String codVenda, 
			@RequestParam(required = false, defaultValue = "0") Integer stsVenda, 
			@RequestParam(required = false, defaultValue = "0") Integer formaPag, 
			@RequestParam(required = false, defaultValue = "") String codProd, 
			@RequestParam(required = false, defaultValue = "") String dtCompra,
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "") String plataforma) {
		
		UserDetailImp userDetails = (UserDetailImp) authentication.getPrincipal();
		
		Usuario user = userDetails.getUser();
		List<Venda> vendas;
	
		String dataFormat = "";
		if(!dtCompra.equals("")) 
			dataFormat = dtCompra.substring(8, 10)+"/"+dtCompra.substring(5, 7)+"/"+dtCompra.substring(0, 4);
		
		Specification<Venda> specs = filtros(stsContato, codVenda, stsVenda, formaPag, codProd, dataFormat, user, plataforma);
		Page<Venda> vendasPage = repo.findAll(specs, PageRequest.of(page, 12, Sort.by(Sort.Direction.DESC, "date")));;
		vendas = vendasPage.getContent();
		model.addAttribute("vendasList", vendasPage);
		
		List<String[]> produt = repo.getProdutoDistinct(user.getId());
		Map<String, String> produtos = new HashMap<String, String>();
		
		for (String[] pro : produt) {
			produtos.put(pro[0], pro[1]);
		}
		
		List<VendaDTO> dtos = new ArrayList<VendaDTO>();
		
		if(vendas.size() > 0)
			for (Venda venda : vendas)
				dtos.add(new VendaDTO(venda, ""));
		
		model.addAttribute("vendas", dtos);
		model.addAttribute("idUsuario", user.getId());
		model.addAttribute("produtos", produtos);
		model.addAttribute("stscontato", StatusContatoEnum.toList());
		model.addAttribute("stsVendaList", StatusVendaEnum.toList());
		model.addAttribute("formaPagList", FormaPagamentoEnum.toList());
		model.addAttribute("produtoSelect", codProd);
		model.addAttribute("stscontatoSelect", stsContato);
		model.addAttribute("stsVendaListSelect", stsVenda);
		model.addAttribute("formaPagListSelect", formaPag);
		model.addAttribute("vendaselect", codVenda);
		model.addAttribute("dataselect", dtCompra);
		model.addAttribute("nome", user.getNome());		
		model.addAttribute("email", user.getEmail());
		model.addAttribute("ultima", vendasPage.getTotalPages());
		model.addAttribute("atual", page);
		model.addAttribute("plataforma", plataforma);
		model.addAttribute("search", "stsContato="+stsContato+
				"&codVenda="+codVenda+
				"&stsVenda="+stsVenda+
				"&formaPag="+formaPag+
				"&codProd="+codProd+
				"&dtCompra="+dtCompra+
				"&plataforma="+plataforma);
		return "boletos";
	}
	
	private Specification<Venda> filtros(Integer stsContato, String codVenda, Integer stsVenda, Integer formaPag,
			String codProd, String dtCompra, Usuario user, String plataforma){
        List<Specification<Venda>> lista = new ArrayList<Specification<Venda>>();
        lista.add((root, query, criteriaBuilder)->criteriaBuilder.equal(root.get("usuario"), user));
        lista.add((root, query, criteriaBuilder)->criteriaBuilder.isNull(root.get("nextId")));
        if(stsContato != 0)
			lista.add((root, query, criteriaBuilder)->criteriaBuilder.equal(root.get("contatoStatusId"), stsContato));
		if(!codVenda.equals(""))
			lista.add((root, query, criteriaBuilder)->criteriaBuilder.equal(root.get("vendaId"), codVenda));
		if(stsVenda != 0)
			lista.add((root, query, criteriaBuilder)->criteriaBuilder.equal(root.get("vendaStatusId"), stsVenda));
		if(formaPag != 0)
			lista.add((root, query, criteriaBuilder)->criteriaBuilder.equal(root.get("formaPagamentosId"), formaPag));
		if(!codProd.equals(""))
			lista.add((root, query, criteriaBuilder)->criteriaBuilder.equal(root.get("codProdu"), codProd));
		if(!dtCompra.equals(""))
			lista.add((root, query, criteriaBuilder)->criteriaBuilder.equal(root.get("dtCompra"), dtCompra));
		if(!plataforma.equals(""))
			lista.add((root, query, criteriaBuilder)->criteriaBuilder.equal(root.get("plataforma"), plataforma.toUpperCase()));
		if(lista.size() == 0)
			return null;
		
		
		Specification<Venda> result = Specification.where(lista.get(0));;
		
		for (int i = 1; i < lista.size(); i++) 
				result = Specification.where(result).and(lista.get(i));
		
		return result;
	}

	@GetMapping(value = {"/detalhe/{id}", "/detalhe/{id}/"})
	public String getVenda(Model model, @PathVariable Long id, Authentication authentication) {
		
		Optional<Venda> venda = repo.findById(id);
		
		Usuario user = getUser(authentication);
		
		String ult = repo.getUltimoContato(repo.getMaxId(venda.get().getVendaId()));
		
		List<MensagemDTO> dtos = new ArrayList<MensagemDTO>();
		VendaDTO dto;
		if(venda.isPresent() && venda.get().getUsuario().getId() == user.getId()) {
			dto = new VendaDTO(venda.get(), ult==null?"":ult);
		
			List<Mensagem> msgs = msgRepo.findAllByUsuarioAndCategoriaAndEnabledTrue(user, venda.get().getFormaPagamentosId()==1?"1":"2");
			
			for (Mensagem mensagem : msgs) {
				mensagem.setMensagem(setaMensagem(mensagem.getMensagem(), venda.get()));
				dtos.add(new MensagemDTO(mensagem));
			}
		}else
			dto = new VendaDTO();
		
		model.addAttribute("venda", dto);
		model.addAttribute("mensagens", dtos);
		model.addAttribute("nome", user.getNome());
		model.addAttribute("email", user.getEmail());
	    
		if(dto.getStatusContatoId() == StatusContatoEnum.AGUARDANDO_CONTATO.getId())
			return "venda";
		else
			return "vendaFinalizada";
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

	@GetMapping(value = {"/detalhe/{id}/contato", "/detalhe/{id}/contato/"})
	public String setContato(Model model, @PathVariable Long id, Authentication authentication) {
		
		Optional<Venda> venda = repo.findById(id);
		
		venda.get().setContatoStatusId(StatusContatoEnum.CONTATADO.getId());
		venda.get().setContatoStatus(StatusContatoEnum.CONTATADO.getDesc());
		
		repo.save(venda.get());
		
		return "redirect:/admin/boleto/detalhe/"+id;
	}
	
	private Usuario getUser(Authentication auth) {
		return ((UserDetailImp) auth.getPrincipal()).getUser();
	}
	
}
