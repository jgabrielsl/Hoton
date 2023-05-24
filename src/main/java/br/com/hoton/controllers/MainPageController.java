package br.com.hoton.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.hoton.models.RecursosVisuais;
import br.com.hoton.models.enums.RecursosVisuaisEnum;
import br.com.hoton.repository.RecursosVisuaisRepository;

@Controller
public class MainPageController {
	
	@Autowired
	private RecursosVisuaisRepository repovisu;

	@GetMapping(value = {"/", ""})
	public String mainPage() {			
			return "index";
	}
	
	@GetMapping(value = {"/screen", "/screen/"})
	public String screenSize(Model model, @RequestParam(required = true, name = "w") Integer width) {
		RecursosVisuais visu = repovisu.findFirstByTypeAndAtivoTrueOrderByIdAsc(RecursosVisuaisEnum.PAG_VENDAS.getId());
		RecursosVisuais tour = repovisu.findFirstByTypeAndAtivoTrueOrderByIdAsc(RecursosVisuaisEnum.PAG_VENDAS_TOUR.getId());
		model.addAttribute("video", visu==null?"":visu.getPath());
		model.addAttribute("videoTour", tour==null?"":tour.getPath());
			if(width <= 750)
				return "mobile";
			else if(width <= 1800)
				return "tablet";
			else
				return "desktop";
	}
}
