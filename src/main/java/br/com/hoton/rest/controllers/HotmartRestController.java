package br.com.hoton.rest.controllers;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hoton.domains.RestDefaultResponse;
import br.com.hoton.exceptions.AtributoPostbackException;
import br.com.hoton.exceptions.TokenUsuarioException;
import br.com.hoton.models.Venda;
import br.com.hoton.rest.domains.Hotmart;
import br.com.hoton.rest.domains.HotmartJson;
import br.com.hoton.services.imp.PlataformasService;

@RestController
public class HotmartRestController {

	@Autowired
	private PlataformasService service;
	
	private static final Logger logger = LogManager.getLogger(HotmartRestController.class);

	@PostMapping(value = { "/api/hotmart/", "/api/hotmart" }, produces = "application/json", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ResponseEntity<RestDefaultResponse> hotmart(@RequestParam(required = false) Map<String, String> params, Hotmart query) {
		String id = ""; 
		try {
			String parametros ="";
			for (String param : params.keySet()) {
				parametros+=param+":"+params.get(param)+",";
			}
			parametros.substring(0, parametros.length()-1);
			logger.info("CONTROLLER: api/hotmart; PARAMETROS: "+parametros+";");
			
			id=query.getTransaction();
			logger.info("CONTROLLER: api/hotmart; TRANSACTION_ID: "+query.getTransaction()+";");
			
			query.verifyParams();
			
			Venda venda = query.obterVenda("HOTMART");
			
			if(venda.getProduto().equalsIgnoreCase("HOTON")) {
				logger.info("CONTROLLER: api/hotmart; TRANSACTION_ID: "+query.getTransaction()+";"+"PROCESSAMENTO CANCELADO-PRODUTO HOTON");
				return new ResponseEntity<RestDefaultResponse>(new RestDefaultResponse("SUCCESSO", ""), HttpStatus.CREATED);
			}
			logger.info("CONTROLLER: api/hotmart; TRANSACTION_ID: "+query.getTransaction()+";" + "INICIANDO PROCESSAMENTO");
			service.processaStatusCompraQuery(venda, query.getHoton_tok(),query.getHottok());
			logger.info("CONTROLLER: api/hotmart; TRANSACTION_ID: "+query.getTransaction()+";" + "FIM PROCESSAMENTO");
		} catch (AtributoPostbackException e) {
			logger.error("CONTROLLER: api/hotmart; TRANSACTION_ID: "+id+"; "+"ERRO AtributoPostbackException: "+e.getMessage()+" - "+e.getCode());
			return new ResponseEntity<RestDefaultResponse>(
					new RestDefaultResponse("ERRO",  e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (TokenUsuarioException e) {
			logger.error("CONTROLLER: api/hotmart; TRANSACTION_ID: "+id+"; "+"ERRO TokenUsuarioException: "+e.getMessage()+" - "+e.getCode());
			return new ResponseEntity<RestDefaultResponse>(
					new RestDefaultResponse("ERRO",  e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("CONTROLLER: api/hotmart; TRANSACTION_ID: "+id+"; "+"ERRO Exception: "+e.getStackTrace()+" - "+e.getMessage()+" - "+e.getCause());
			return new ResponseEntity<RestDefaultResponse>(
					new RestDefaultResponse("ERROR", e.getCause() + ": " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<RestDefaultResponse>(new RestDefaultResponse("SUCCESSO", ""), HttpStatus.CREATED);
	}
	
	@PostMapping(value = { "/api/hotmart/", "/api/hotmart" }, produces = "application/json", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<RestDefaultResponse> hotmartJson(@RequestParam(required = false) Map<String, String> params, 
			@RequestBody(required = false) HotmartJson json, 
			@RequestParam(required = false) String hoton_tok) {
		/*try {
			try {
				if(json != null)json.verifyParams(hoton_tok);
				else throw new Exception("ERRO", new Throwable("REQUISIÇÃO INVÁLIDA"));
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<RestDefaultResponse>(
						new RestDefaultResponse("ERRO", e.getCause() + ": " + e.getMessage()),
						HttpStatus.BAD_REQUEST);
			}
			service.processaStatusCompraJson(json);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RestDefaultResponse>(
					new RestDefaultResponse("ERROR", e.getCause() + ": " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
		return new ResponseEntity<RestDefaultResponse>(new RestDefaultResponse("EVENTO_NAO_SUPORTADO", ""), HttpStatus.BAD_REQUEST);
	}
}
