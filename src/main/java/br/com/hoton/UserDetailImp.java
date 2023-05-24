package br.com.hoton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.hoton.models.Role;
import br.com.hoton.models.Usuario;

public class UserDetailImp implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private Usuario user;
	
	public UserDetailImp(Usuario user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.isEnabled();
	}
	
	public String getClientSecret() {
		return user.getClientSecret();
	}
	
	public String getClientId() {
		return user.getClientId();
	}
	
	public String getClientBasic() {
		return user.getBasic();
	}
	
	public String getUsernome() {
		return user.getNome();
	}
	
	public Usuario getUser() {
		return new Usuario(user.getId(), user.getEmail(), user.getHotmartToken(), user.getHotonTok(), user.getMonetizzeToken(), user.getEduzzToken() , user.getNome(), user.getRoles());
	}
	
	public void updateUser(Usuario user) {
		this.user = user;
	}
}
