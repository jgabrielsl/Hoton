package br.com.hoton.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.hoton.models.Mensagem;
import br.com.hoton.models.Usuario;

public interface MensagemRepository extends CrudRepository<Mensagem, Long> {

	public List<Mensagem> findAllByUsuarioAndEnabledTrue(Usuario user);

	public List<Mensagem> findAllByUsuarioAndCategoriaAndEnabledTrue(Usuario user, String categoria);
}
