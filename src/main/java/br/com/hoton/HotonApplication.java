package br.com.hoton;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.hoton.models.Role;
import br.com.hoton.models.Usuario;
import br.com.hoton.models.enums.DisparadorStatus;
import br.com.hoton.models.whatsapp.ContatoStatus;
import br.com.hoton.models.whatsapp.MsgWhatsappType;
import br.com.hoton.repository.ContatoStatusRepo;
import br.com.hoton.repository.DisparadorStatusRepo;
import br.com.hoton.repository.MsgTypeRepo;
import br.com.hoton.repository.RoleRepository;
import br.com.hoton.repository.UsuarioRepository;

@SpringBootApplication
public class HotonApplication {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private ContatoStatusRepo repo2;
	
	public static final Logger LOG = LogManager.getLogger(HotonApplication.class);
	
	@Autowired
	private RoleRepository repoR;
	
	@Autowired
	private DisparadorStatusRepo dispR;
	
	@Autowired
	private MsgTypeRepo confT;

	public static void main(String[] args) {
		SpringApplication.run(HotonApplication.class, args);
	}

	@Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
        	
        	Role role = repoR.getRoleByName("ADMIN");
        	if(role == null) {
        		role = new Role();
            	role.setName("ADMIN");
            	role = repoR.save(role);
        	}
        	
        	Role roleUser = repoR.getRoleByName("USER");
        	if(roleUser == null) {
        		roleUser = new Role();
        		roleUser.setName("USER");
        		roleUser = repoR.save(roleUser);
        	}
        	
        	Role roleWhats = repoR.getRoleByName("WHATS");
        	if(roleWhats == null) {
        		roleWhats = new Role();
        		roleWhats.setName("WHATS");
        		roleWhats = repoR.save(roleWhats);
        	}
            Usuario user = new Usuario();
            user.setEmail("VICTOR.RAFALUCCA@GMAIL.COM");
            user.setPassword(passwordEncoder().encode("1234"));
            Set<Role> set= new HashSet<>();
            set.add(role);
            set.add(roleUser);
            set.add(roleWhats);
            user.setRoles(set);
            user.setEnabled(true);
            user.setId(1l);
            System.out.println("Usuario 1 - inicinado save");
            repo.save(user);
            System.out.println("Usuario 1 - save completo");
            /*Usuario user1 = new Usuario();
            user1.setEmail("JONATANDRUMOND@YAHOO.COM.BR");
            user1.setPassword(passwordEncoder().encode("1234"));
            set= new HashSet<>();
            set.add(role);
            set.add(roleUser);
            set.add(roleWhats);
            user1.setRoles(set);
            user1.setEnabled(true);
            user1.setId(2l);
            System.out.println("Usuario 2 - inicinado save");
            repo.save(user1);*/
            System.out.println("Usuario 2 - save completo");
        	
        	repo2.saveAll(ContatoStatus.getList());
			
			confT.saveAll(MsgWhatsappType.getList());
			
			dispR.saveAll(DisparadorStatus.getList());
        };
    }
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailServiceImp();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
}
