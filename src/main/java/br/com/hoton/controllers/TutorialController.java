package br.com.hoton.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hoton.UserDetailImp;
import br.com.hoton.models.RecursosVisuais;
import br.com.hoton.models.Usuario;
import br.com.hoton.models.enums.RecursosVisuaisEnum;
import br.com.hoton.repository.RecursosVisuaisRepository;
import br.com.hoton.services.imp.AcoesServiceImp;

@Controller
@RequestMapping(value = { "/admin/tutorial", "/admin/tutorial/" })
public class TutorialController {
	
	@Autowired
	private RecursosVisuaisRepository repo;
	
	@Autowired
	private AcoesServiceImp servAct;
	
	@GetMapping(value = {"/", ""})
	public String index(Model model, Authentication authentication) {
		
		List<RecursosVisuais> visuais = repo.findByTypeAndAtivoTrue(RecursosVisuaisEnum.TUTORIAL.getId());
		
		model.addAttribute("visuais", visuais);
		model.addAttribute("nome", getUser(authentication).getNome());
		
		return "tutorial";
	}

	private Usuario getUser(Authentication auth) {
		return ((UserDetailImp) auth.getPrincipal()).getUser();
	}
}
