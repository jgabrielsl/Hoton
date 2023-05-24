package br.com.hoton.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.hoton.models.Usuario;
import br.com.hoton.models.Venda;

public interface VendaRepository extends CrudRepository<Venda, Long>, JpaSpecificationExecutor<Venda> {

	public List<Venda> findAllByUsuarioAndNextIdIsNullOrderByIdDesc(Usuario user, Pageable page, Specification<Venda> specs);

	public List<Venda> findAllByUsuarioAndNextIdIsNullOrderByIdDesc(Usuario user, Pageable page);
	
	@Query("SELECT DISTINCT codProdu, produto FROM Venda WHERE usuario_id = :id and nextId IS NULL")
	public List<String[]> getProdutoDistinct(@Param("id")Long id);

	public List<Optional<Venda>> findByVendaIdAndNextIdIsNullAndIdNotOrderByIdDesc(String vendaId, Long Id);
	
	@Query("SELECT v.dtContato FROM Venda v WHERE v.id = :id")
	public String getUltimoContato(@Param("id")Long id);
	
	@Query("SELECT MAX(ve.id) FROM Venda ve WHERE ve.vendaId = :vendaId")
	public Long getMaxId(@Param("vendaId")String vendaId);
	
	Optional<Venda> findByNextId(Long id);
	
	//Optional<Venda> findByDddAndTelefoneAndCodProdu(String ddd, String telefone, String codProduto);
	
	Optional<Venda> findByDddAndTelefoneAndCodProduAndFormaPagamentosIdAndDateGreaterThanEqual(String ddd, String telefone, String codProduto, Integer formaPagamentosId, LocalDate date);
}
