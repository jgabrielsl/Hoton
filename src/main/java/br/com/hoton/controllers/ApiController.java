package br.com.hoton.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.hoton.UserDetailImp;
import br.com.hoton.models.Usuario;
import br.com.hoton.repository.UsuarioRepository;
import br.com.hoton.services.imp.AcoesServiceImp;
import br.com.hoton.services.imp.EncryptService;

@Controller
@RequestMapping(value = {"/admin/api/", "/admin/api"})
@SessionAttributes("user")
public class ApiController {
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private EncryptService enc;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private AcoesServiceImp servAct;
	
	@GetMapping(value = {"/", ""})
	public String settings(Model model, Authentication authentication) {
		
		Usuario user = getUser(authentication);
		
		model.addAttribute("hotonApiKey", user.getHotonTok());
		model.addAttribute("hotmartApiKey", user.getHotmartToken());
		model.addAttribute("monetizzeApiKey", user.getMonetizzeToken());
		model.addAttribute("eduzzApiKey", user.getEduzzToken());
		
		if(user.getEmail().toUpperCase().equals("JONATANDRUMOND@YAHOO.COM.BR")) {
			model.addAttribute("urlAdmin", env.getProperty("basePath")+"/api/hoton?hoton_tok="+user.getHotonTok());
			model.addAttribute("urlHotmart", env.getProperty("basePath")+"/api/hotmart?hoton_tok="+user.getHotonTok());
		}else
			model.addAttribute("urlHotmart",  env.getProperty("basePath")+"/api/hotmart?hoton_tok="+user.getHotonTok());
		
		model.addAttribute("urlMonetizze",  env.getProperty("basePath")+"/api/monetizze?hoton_tok="+user.getHotonTok());
		model.addAttribute("urlEduzz",  env.getProperty("basePath")+"/api/eduzz?hoton_tok="+user.getHotonTok());
		
		model.addAttribute("nome", user.getNome());
		model.addAttribute("email", user.getEmail());
		
		return "settings";
	}
	
	@PostMapping(value = {"/", ""})
	public String configurarHoton(Model model, Authentication authentication) {
	
		Optional<Usuario> userOpt = userRepo.findById(getUser(authentication).getId());
		
		if(userOpt.isPresent()) {
			try {
				userOpt.get().setHotonTok(enc.simpleEncrypt(userOpt.get().getEmail(), userOpt.get().getNome()));
				Usuario user = userRepo.save(userOpt.get());
				
				setReturnModel(model, user);
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("nome", userOpt.get().getNome());
		model.addAttribute("email", userOpt.get().getEmail());
		
		return "settings";
	}
	
	@PostMapping(value = {"/{plataforma}"})
	public String configurarHotmart(Model model, Authentication authentication, @PathVariable String plataforma, @RequestParam String token) {
	
		Optional<Usuario> userOpt = userRepo.findById(getUser(authentication).getId());
		
		if(userOpt.isPresent() && token != null) {
			try {
				switch (plataforma.toLowerCase()) {
				case "hotmart":
					userOpt.get().setHotmartToken(token);
					break;
				case "eduzz":
					userOpt.get().setEduzzToken(token);
					break;
				case "monetizze":
					userOpt.get().setMonetizzeToken(token);
					break;
				default:
					setReturnModel(model, userOpt.get());
					return "settings";
				}
				
				Usuario user = userRepo.save(userOpt.get());
				((UserDetailImp) authentication.getPrincipal()).updateUser(user);
				setReturnModel(model, user);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("nome", userOpt.get().getNome());		
		model.addAttribute("email", userOpt.get().getEmail());
		return "settings";
	}
	
	private void setReturnModel(Model model, Usuario user) {
		
		model.addAttribute("hotonApiKey", user.getHotonTok());
		model.addAttribute("hotmartApiKey", user.getHotmartToken());
		model.addAttribute("monetizzeApiKey", user.getMonetizzeToken());
		model.addAttribute("eduzzApiKey", user.getEduzzToken());
		
		if(user.getEmail().toUpperCase().equals("JONATANDRUMOND@YAHOO.COM.BR")) {
			model.addAttribute("urlAdmin", env.getProperty("basePath")+"/api/hoton?hoton_tok="+user.getHotonTok());
			model.addAttribute("urlHotmart", env.getProperty("basePath")+"/api/hotmart?hoton_tok="+user.getHotonTok());
		}else
			model.addAttribute("urlHotmart",  env.getProperty("basePath")+"/api/hotmart?hoton_tok="+user.getHotonTok());
		
		model.addAttribute("urlMonetizze",  env.getProperty("basePath")+"/api/monetizze?hoton_tok="+user.getHotonTok());
		model.addAttribute("urlEduzz",  env.getProperty("basePath")+"/api/eduzz?hoton_tok="+user.getHotonTok());
		
	}

	private Usuario getUser(Authentication auth) {
		return ((UserDetailImp) auth.getPrincipal()).getUser();
	}

}
