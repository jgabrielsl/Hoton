package br.com.hoton.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.hoton.UserDetailImp;
import br.com.hoton.models.RecursosVisuais;
import br.com.hoton.models.Usuario;
import br.com.hoton.models.enums.RecursosVisuaisEnum;
import br.com.hoton.repository.RecursosVisuaisRepository;
import br.com.hoton.services.imp.AcoesServiceImp;

@Controller
@RequestMapping(value = { "/admin/gerenciar/midia", "/admin/gerenciar/midia/" })
public class GerenciadorMidiaController {

	@Autowired
	private RecursosVisuaisRepository repo;
	
	@Autowired
	private AcoesServiceImp servAct;
	
	@GetMapping(value = {"/", ""})
	public String index(Model model, Authentication authentication) {
		
		List<RecursosVisuais> visuais = repo.findByTypeOrderByOrdem(RecursosVisuaisEnum.TUTORIAL.getId());
		
		RecursosVisuais rec = repo.findFirstByTypeAndAtivoTrueOrderByIdAsc(RecursosVisuaisEnum.PAG_VENDAS.getId());
		
		Integer maxordem = repo.findMaxByType(RecursosVisuaisEnum.TUTORIAL.getId());
		
		model.addAttribute("vendaPath", rec==null?"":rec.getPath());
		model.addAttribute("vendaPathId", rec==null?"":rec.getId());
		model.addAttribute("visuais", visuais);
		model.addAttribute("max", maxordem==null?0:maxordem);
		model.addAttribute("nome", getUser(authentication).getNome());
		
		return "gerenciadorMidia";
	}
	
	@PostMapping(value = {"/delete/{id}", "/delete/{id}/"}, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> delete(Model model, Authentication authentication, @PathVariable Long id) {
		try {
			Optional<RecursosVisuais> rec= repo.findById(id);
			
			if(rec.isPresent())
				repo.deleteById(rec.get().getId());
			else
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PostMapping(value = {"/inserir/", "/inserir"}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<RecursosVisuais>> insert(Model model, Authentication authentication, @RequestBody List<RecursosVisuais> visuais) {
		try {
			for (RecursosVisuais rec : visuais) {
				if(rec.getId() == null || rec.getId() == -1)
					rec.setId(null);
			}
			visuais = StreamSupport
					  .stream(repo.saveAll(visuais).spliterator(), false).collect(Collectors.toList());
		}catch (Exception e) {
			return new ResponseEntity<List<RecursosVisuais>>(visuais, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<RecursosVisuais>>(visuais, HttpStatus.OK);
	}
	
	@PostMapping(value = {"/inserir/venda", "/inserir/venda/"}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> insertVenda(Model model, Authentication authentication, @RequestBody RecursosVisuais visuais) {
		try {
			repo.save(visuais);
		}catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PostMapping(value = {"/inserir/tour", "/inserir/tour/"}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> insertTour(Model model, Authentication authentication, @RequestBody RecursosVisuais tour) {
		try {
			repo.save(tour);
		}catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	private Usuario getUser(Authentication auth) {
		return ((UserDetailImp) auth.getPrincipal()).getUser();
	}
}
