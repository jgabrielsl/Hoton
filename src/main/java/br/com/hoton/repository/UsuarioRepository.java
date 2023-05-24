package br.com.hoton.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.hoton.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario getUserByEmail(@Param("email") String email);

	public Usuario findByEmail(String email);

	public Usuario findByHotonTokAndHotmartToken(String hotonTok, String hotmartToken);

	public Usuario findByHotonTok(String hoton_tok);

	public Usuario findByEmailIgnoreCase(String string);

	public Usuario findByCodAssinante(String subscriberCode);

	public Usuario findByHotonTokAndMonetizzeToken(String hotonTok, String platToken);

	public Usuario findByHotonTokAndEduzzToken(String hotonTok, String platToken);
}
