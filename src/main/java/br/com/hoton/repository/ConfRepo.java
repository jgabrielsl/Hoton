package br.com.hoton.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.hoton.models.Usuario;
import br.com.hoton.models.whatsapp.Conf;

public interface ConfRepo extends CrudRepository<Conf, Long>{

	Conf findByUsuario(Usuario usuario);

	Conf findByIdAndUsuario(Long id, Usuario user);

}
