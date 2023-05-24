package br.com.hoton.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.hoton.UserDetailImp;
import br.com.hoton.models.Usuario;
import br.com.hoton.models.Venda;
import br.com.hoton.models.dto.VendaDTO;
import br.com.hoton.repository.UsuarioRepository;
import br.com.hoton.repository.VendaRepository;
import br.com.hoton.services.imp.AcoesServiceImp;
import br.com.hoton.services.imp.EmailServiceImp;

@Controller
public class LoginController {
	
	@Autowired
	private VendaRepository repo;
	
	@Autowired
	private UsuarioRepository repoU;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AcoesServiceImp servAct;

	@Autowired
	private EmailServiceImp emailServ;
	
	@GetMapping(value = {"/login", "/login/"})
	public String login(Authentication authentication, @RequestParam(required = false, defaultValue = "false") String error) {
		
		if(authentication != null && authentication.isAuthenticated())
			return "redirect:/admin/home/";
		
		return "login";
	}
	
	@PostMapping(value = {"/resetSenha", "/resetSenha/"})
	public String resetSenha(Authentication authentication, @RequestParam(required = false, defaultValue = "false") String email) {
		
		Usuario user = repoU.findByEmail(email.toUpperCase());
		try {
		if(user != null) {
			String nvsenha = generateKey();
			user.setPassword(passwordEncoder.encode(nvsenha));
			user = repoU.save(user);
			
			emailServ.enviarEmailSimples(
					user.getEmail(),"ALTERAÇÃO DE SENHA","Olá, \n\nSua senha foi alterada com sucesso!\n\nSENHA: "+nvsenha);
			
			return "redirect:/login?reset=true";
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/login?reset=false";
	}
	
	@GetMapping("/admin/home")
	public String home(Model model, Authentication authentication) {	
		Usuario user = getUser(authentication);
		
		List<Venda> vendas = repo.findAllByUsuarioAndNextIdIsNullOrderByIdDesc(user, PageRequest.of(0, 4));
		
		List<VendaDTO> dtos = new ArrayList<VendaDTO>();
		
		if(vendas.size() > 0)
			for (Venda venda : vendas) {
				dtos.add(new VendaDTO(venda, ""));
			}
		
		model.addAttribute("vendas", dtos);
		
		model.addAttribute("nome", user.getNome());
		model.addAttribute("email", user.getEmail());
		
		servAct.gravaAcoes("LOGGIN");
		
		return "home";
	}	
	
	private Usuario getUser(Authentication auth) {
		return ((UserDetailImp) auth.getPrincipal()).getUser();
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
}
