package br.com.hoton.rest.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hoton.domains.RestDefaultResponse;
import br.com.hoton.rest.domains.Hotmart;
import br.com.hoton.rest.domains.HotmartJson;
import br.com.hoton.services.imp.HotonService;

@RestController
public class HotonRestController {
	
	@Autowired
	private HotonService service;

	@PostMapping(value = {"/api/hoton/", "/api/hoton"}, produces = "application/json", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ResponseEntity<RestDefaultResponse>  hotonAdmin(@RequestParam(required = false) Map<String, String> params, 
			Hotmart query) {
		//@RequestBody(required = false) 
		try {
			if(query!=null && query.getStatus() != null) {
				try{
					query.verifyParams();
				}catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<RestDefaultResponse>(
							new RestDefaultResponse("ERRO", e.getCause()+": "+e.getMessage()), 
							HttpStatus.BAD_REQUEST);
				}
				service.processaStatusCompraQuery(query.obterVenda("HOTON"), query.getHoton_tok(), query.getHottok());	
			}					
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RestDefaultResponse>(
					new RestDefaultResponse("ERROR", e.getCause()+": "+e.getMessage()), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<RestDefaultResponse>(new RestDefaultResponse("SUCCESSO", ""), HttpStatus.CREATED);
	}
	
	@PostMapping(value = { "/api/hoton/", "/api/hoton" }, produces = "application/json", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<RestDefaultResponse> hotmartJson(@RequestParam(required = false) Map<String, String> params, 
			@RequestBody(required = false) HotmartJson json, 
			@RequestParam(required = false) String hoton_tok) {
		try{
			
			if(json == null)
				return new ResponseEntity<RestDefaultResponse>(
						new RestDefaultResponse("ERRO", "REQUISICAO INVALIDA"),
						HttpStatus.BAD_REQUEST);
			
			try {
				if(json != null)json.verifyParams(hoton_tok);
				service.processaStatusCompraJson(json);
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
		}
		return new ResponseEntity<RestDefaultResponse>(new RestDefaultResponse("SUCCESSO", ""), HttpStatus.CREATED);
	}
}
