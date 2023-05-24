package br.com.hoton.services.imp;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import br.com.hoton.UserDetailImp;
import br.com.hoton.models.RastreamentoAcoes;
import br.com.hoton.models.Usuario;
import br.com.hoton.repository.RastreamentoAcoesRepository;

@Service
@SessionScope
public class AcoesServiceImp {

	@Autowired
	RastreamentoAcoesRepository repo;
	
	private @Autowired HttpServletRequest request;
	
	private static final Logger logger = LogManager.getLogger(AcoesServiceImp.class);
	
	public void gravaAcoes(String acao) {
		try {
			Usuario user= ((UserDetailImp)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
			RastreamentoAcoes acoes = new RastreamentoAcoes(request.getRemoteAddr(), user, acao, LocalDateTime.now());
			logger.info("RastreamentoAcoes -> "+acoes.toString());
			repo.save(acoes);
		}catch (Exception e) {
			logger.error("ERRO RastreamentoAcoes -> "+request.getRemoteAddr()+";"+acao);
			e.printStackTrace();
		}
	}
	
}
