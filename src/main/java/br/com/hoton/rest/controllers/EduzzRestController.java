package br.com.hoton.rest.controllers;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hoton.domains.RestDefaultResponse;
import br.com.hoton.exceptions.AtributoPostbackException;
import br.com.hoton.exceptions.TokenUsuarioException;
import br.com.hoton.models.Venda;
import br.com.hoton.rest.domains.Eduzz;
import br.com.hoton.services.imp.PlataformasService;

@RestController
public class EduzzRestController {

	@Autowired
	private PlataformasService service;
	
	private static final Logger logger = LogManager.getLogger(EduzzRestController.class);
	
	@PostMapping(value = { "/api/eduzz/", "/api/eduzz" })
	public ResponseEntity<RestDefaultResponse> eduzz(@RequestParam(required = false) Map<String, String> params) {
		String id = ""; 
		try {
			String parametros ="";
			for (String param : params.keySet()) {
				parametros+=param+":"+params.get(param)+",";
			}
			parametros.substring(0, parametros.length()-1);
			logger.info("CONTROLLER: api/eduzz; PARAMETROS: "+parametros+";");
			
			Eduzz domain = new Eduzz(params);

			id=domain.getTrans_cod();
			logger.info("CONTROLLER: api/eduzz; TRANSACTION_ID: "+domain.getTrans_cod()+";");
			
			domain.verifyParams();
			
			Venda venda = domain.obterVenda("EDUZZ");
			
			logger.info("CONTROLLER: api/eduzz; TRANSACTION_ID: "+domain.getTrans_cod()+";" + "INICIANDO PROCESSAMENTO");
			service.processaStatusCompraQuery(venda, domain.getHoton_tok(), domain.getApi_key());
			logger.info("CONTROLLER: api/eduzz; TRANSACTION_ID: "+domain.getTrans_cod()+";" + "FIM PROCESSAMENTO");
		} catch (AtributoPostbackException e) {
			logger.error("CONTROLLER: api/eduzz; TRANSACTION_ID: "+id+"; "+"ERRO: "+e.getMessage()+" - "+e.getCode());
			return new ResponseEntity<RestDefaultResponse>(
					new RestDefaultResponse("ERRO",  e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (TokenUsuarioException e) {
			logger.error("CONTROLLER: api/eduzz; TRANSACTION_ID: "+id+"; "+"ERRO: "+e.getMessage()+" - "+e.getCode());
			return new ResponseEntity<RestDefaultResponse>(
					new RestDefaultResponse("ERRO",  e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("CONTROLLER: api/eduzz; TRANSACTION_ID: "+id+"; "+"ERRO: "+e.getStackTrace().toString()+" - "+e.getMessage()+" - "+e.getCause());
			return new ResponseEntity<RestDefaultResponse>(
					new RestDefaultResponse("ERROR", e.getCause() + ": " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<RestDefaultResponse>(new RestDefaultResponse("SUCCESSO", ""), HttpStatus.CREATED);
	}
	
}
