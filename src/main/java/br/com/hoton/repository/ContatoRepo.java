package br.com.hoton.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.hoton.models.Disparador;
import br.com.hoton.models.Usuario;
import br.com.hoton.models.Venda;
import br.com.hoton.models.whatsapp.Conf;
import br.com.hoton.models.whatsapp.Contato;
import br.com.hoton.models.whatsapp.ContatoStatus;

public interface ContatoRepo extends CrudRepository<Contato, Long>{

	Optional<Contato> findByVenda(Venda venda);
	
	Optional<List<Contato>> findAllByVenda(Venda venda);

	List<Contato> findTop20ByConfAndStatusOrderByIdDesc(Conf user, ContatoStatus contato);

	List<Contato> findTop20ByConfAndStatusInOrderByIdDesc(Conf user, List<ContatoStatus> contatos);

	List<Contato> findAllByDisparador(Disparador disparador);
}
