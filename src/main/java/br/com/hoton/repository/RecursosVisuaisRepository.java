package br.com.hoton.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.hoton.models.RecursosVisuais;

public interface RecursosVisuaisRepository extends CrudRepository<RecursosVisuais, Long> {

	public List<RecursosVisuais> findByTypeAndAtivoTrue(Integer type);

	public List<RecursosVisuais> findByTypeOrderByOrdem(Integer type);

	@Query(value = "SELECT max(ordem) FROM RecursosVisuais where type = :type")
	public Integer findMaxByType(Integer type);
	
	public RecursosVisuais findFirstByTypeAndAtivoTrueOrderByIdAsc(Integer type);
}
