package br.com.hoton.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.hoton.UserDetailImp;
import br.com.hoton.models.Usuario;
import br.com.hoton.repository.UsuarioRepository;
import br.com.hoton.services.imp.AcoesServiceImp;

@Controller
@RequestMapping(value={"/admin/user/", "/admin/user"})
public class UserController {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AcoesServiceImp servAct;
	
	@GetMapping(value = {"/", ""})
	public String user(Model model, Authentication authentication) {	
		
		String nome = "";
		String numero = "";
		String email = "";
		String id = "";
		
		try {
			Optional<Usuario> user = repo.findById(getUser(authentication).getId());
		
			if(user.isPresent()) {
				nome = user.get().getNome();
				numero = user.get().getTelefone();
				email = user.get().getEmail();
				id = user.get().getId().toString();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("nome", nome);
		model.addAttribute("numero", numero);
		model.addAttribute("email", email);
		model.addAttribute("id", id);
		servAct.gravaAcoes("LOGGIN");
		return "user";
	}
	
	@PostMapping(value = {"/valid/", "/valid"})
	@ResponseBody
	public String validPass(@RequestParam(required = true, defaultValue = "") String pass, Authentication authentication) {	
		try {
			Optional<Usuario> user = repo.findById(getUser(authentication).getId());
			
			if(user.isPresent() && passwordEncoder.matches(pass, user.get().getPassword()))
				return "true";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "false";
	}
	
	@PostMapping(value={"/edit", "/edit/"})
	public String edit(Model model, Authentication authentication, @RequestParam(required = false, defaultValue = "") String nome,
									@RequestParam(required = false, defaultValue = "") String numero,
									@RequestParam(required = false, defaultValue = "") String oldSenha,
									@RequestParam(required = false, defaultValue = "") String newSenha,
									@RequestParam(required = false, defaultValue = "") String confirmSenha,
									@RequestParam(required = false, defaultValue = "") String id,
									@RequestParam(required = false, defaultValue = "false") boolean alterSenha) {
		
		try {
			Optional<Usuario> user = repo.findById(getUser(authentication).getId());
		
			if(user.isPresent()) {
				if(alterSenha && !oldSenha.equals("") && 
						!newSenha.equals("") && 
						!confirmSenha.equals("") &&
						!oldSenha.equals(newSenha) &&
						newSenha.equals(confirmSenha) &&
						passwordEncoder.matches(oldSenha, user.get().getPassword())) {
					user.get().setPassword(passwordEncoder.encode(newSenha));
				}
				user.get().setTelefone(numero);
				user.get().setNome(nome);
				Usuario usu = repo.save(user.get());
				((UserDetailImp) authentication.getPrincipal()).updateUser(usu);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		servAct.gravaAcoes("EDIT_USUARIO");
		return "redirect:/admin/user/";
	}

	private Usuario getUser(Authentication auth) {
		return ((UserDetailImp) auth.getPrincipal()).getUser();
	}
	
}
