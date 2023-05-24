package br.com.hoton.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.hoton.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	@Query("SELECT u FROM Role u WHERE u.name = :name")
    public Role getRoleByName(@Param("name") String name);
	
}
