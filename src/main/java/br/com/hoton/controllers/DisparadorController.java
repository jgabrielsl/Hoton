package br.com.hoton.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.hoton.UserDetailImp;
import br.com.hoton.domains.RestDefaultResponse;
import br.com.hoton.models.Disparador;
import br.com.hoton.models.Usuario;
import br.com.hoton.models.dto.DisparadorUpload;
import br.com.hoton.models.whatsapp.Contato;
import br.com.hoton.repository.ContatoRepo;
import br.com.hoton.repository.DisparadorRepo;

@Controller
@RequestMapping(value={"/admin/whatsapp/disparador", "/admin/whatsapp/disparador/"})
public class DisparadorController {

	@Autowired
	private DisparadorRepo dispRepo;
	
	@Autowired
	private ContatoRepo contRepo;
	
	@Autowired
	private Environment env;
	
	@GetMapping(value = {"/", ""})
	public String index(Model model, Authentication authentication) {	
		return "vue/index";
	}	
	
	@PostMapping(value = {"/upload", "/upload/"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<RestDefaultResponse> upload(@RequestBody DisparadorUpload up, Authentication authentication) {
		try {
			up.isValid();

			String fileName = "upload"+LocalDateTime.now().toEpochSecond(ZoneOffset.MIN)+".xlsx";

			Usuario user = getUser(authentication);
			
			Disparador disp = up.build(fileName, user);
			if(disp.isProcessed())
				disp.setDtDisparo(LocalDateTime.now());
			if(dispRepo.findAllByUserAndDtDisparoGreaterThanEqualAndDtDisparoLessThanEqual(user, disp.getDtDisparo().minusDays(1), disp.getDtDisparo().plusDays(1)).size() > 0)
				return new ResponseEntity<RestDefaultResponse>(new RestDefaultResponse("ERROR", "Já há um agendamento para o período"), HttpStatus.BAD_REQUEST);
			File directory = new File(env.getProperty("disparo"));
			if(!directory.exists())
				directory.mkdirs();
			directory = new File(env.getProperty("disparo")+fileName);
			if(!directory.exists())
				directory.createNewFile();
			String fileLocation = directory.getAbsolutePath();
			System.out.println(fileLocation);
			FileOutputStream output = new FileOutputStream(fileLocation);

			output.write(Base64.getDecoder().decode(up.getFile()));

			output.close();
			
			dispRepo.save(disp);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RestDefaultResponse>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<RestDefaultResponse>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = {"get/", "get"}, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<List<Disparador>> getDisparos(
                        @RequestParam(defaultValue = "0") Integer page, 
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "id") String sort,
                        Authentication authentication) {
		Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sort));
        Page<Disparador> list = dispRepo.findAllByUser(paging, getUser(authentication));
 
        if(list.hasContent())
        	return new ResponseEntity<List<Disparador>>(list.getContent(), HttpStatus.OK); 
        else
        	return new ResponseEntity<List<Disparador>>(new ArrayList<Disparador>(), HttpStatus.OK); 
    }
	
	@GetMapping(value = {"get/detalhe/{id}", "get/detalhe/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<List<Contato>> getDisparoDetalhe(Authentication authentication, @PathVariable("id") Long id) {
		Optional<Disparador> disp = dispRepo.findByIdAndUser(id, getUser(authentication));
		
		if(disp.isPresent()) {
			List<Contato> list = contRepo.findAllByDisparador(disp.get());
			 
	        if(list.size() > 0)
	        	return new ResponseEntity<List<Contato>>(list, HttpStatus.OK); 
	        else
	        	return new ResponseEntity<List<Contato>>(new ArrayList<Contato>(), HttpStatus.OK); 
		}
		return new ResponseEntity<List<Contato>>(new ArrayList<Contato>(), HttpStatus.OK); 
    }
	
	@RequestMapping("/download/{id}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long id, Authentication authentication) throws IOException {
		
		Optional<Disparador> disp = dispRepo.findByIdAndUser(id, getUser(authentication));
		
		if(disp.isPresent()) {
			File file = new File(env.getProperty("disparo")+disp.get().getFileName());
			if (file.exists()) {
				String mimeType = URLConnection.guessContentTypeFromName(file.getName());
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
	
				response.setContentType(mimeType);
	
				response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
	
				response.setContentLength((int) file.length());
	
				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	
				FileCopyUtils.copy(inputStream, response.getOutputStream());
	
			}
		}else {
			
		}
	}
	
	private Usuario getUser(Authentication auth) {
		return ((UserDetailImp) auth.getPrincipal()).getUser();
	}
}
