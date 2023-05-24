package br.com.hoton.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.hoton.UserDetailImp;
import br.com.hoton.models.Mensagem;
import br.com.hoton.models.Usuario;
import br.com.hoton.models.dto.MensagemDTO;
import br.com.hoton.repository.MensagemRepository;
import br.com.hoton.services.imp.AcoesServiceImp;

@Controller
@RequestMapping(value = { "/admin/mensagem", "/admin/mensagem/" })
public class MensagemController {

	@Autowired
	private MensagemRepository repo;
	
	@Autowired
	private AcoesServiceImp servAct;

	@GetMapping(value = {"/", ""})
	public String index(Model model, Authentication authentication) {
		
		Usuario user = getUser(authentication);
		
		List<Mensagem> mensagens = repo.findAllByUsuarioAndEnabledTrue(user);
		List<MensagemDTO> dtos = new ArrayList<MensagemDTO>();
		for (Mensagem mensagem : mensagens)
			if(mensagem.isEnabled())
				dtos.add(new MensagemDTO(mensagem));
		
		model.addAttribute("id", user.getId());
		model.addAttribute("mensagens", dtos);
		model.addAttribute("nome", user.getNome());
		model.addAttribute("email", user.getEmail());
		
		return "mensagens";
	}

	@GetMapping(value = { "/edit/{id}", "/edit/{id}/", "/edit"})
	public String edit(Model model, @PathVariable(required = false) Long id, Authentication authentication) {
		
		Usuario user = getUser(authentication);
		
		try {
			if(id != null) {
				Optional<Mensagem> mensagem = repo.findById(id);
				if(mensagem.isPresent()) {
					MensagemDTO dto = new MensagemDTO(mensagem.get());
					model.addAttribute("msg", dto);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("nome", user.getNome());
		model.addAttribute("email", user.getEmail());
		
		return "mensagemEdit";
	}
	
	@PostMapping(value = { "/cadastrar/", "/cadastrar" })
	public String cadastrar(Model model, Authentication authentication,
			@RequestParam(required = false) String categoria,
			@RequestParam(required = false) String titulo,
			@RequestParam(required = false) String mensagem,
			@RequestParam(required = false, defaultValue = "-1") Long id) {
		
		try {
			Mensagem msg;
			if(id == -1) {
				msg = new Mensagem();
				msg.setUsuario(getUser(authentication));
			}else {
				msg = repo.findById(id).get();
			}
			msg.setCategoria(categoria);
			msg.setEnabled(true);
			msg.setTitulo(titulo);
			msg.setMensagem(mensagem.substring(0, mensagem.length()>2000?2000:mensagem.length()));
			repo.save(msg);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/mensagem";
	}
	
	@GetMapping(value = { "/excluir/{id}", "/excluir/{id}/" })
	public String disable(Model model, @PathVariable(required = true) Long id, Authentication authentication) {
		
		try {
			Optional<Mensagem> mensagem = repo.findById(id);
			if(mensagem.isPresent()) {
				mensagem.get().setEnabled(false);
				repo.save(mensagem.get());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/mensagem";
	}

	private Usuario getUser(Authentication auth) {
		return ((UserDetailImp) auth.getPrincipal()).getUser();
	}

}
