package br.com.hoton.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.hoton.models.Disparador;
import br.com.hoton.models.Usuario;
import br.com.hoton.models.enums.DisparadorStatus;

public interface DisparadorRepo extends PagingAndSortingRepository<Disparador, Long>{

	Page<Disparador> findAllByUser(Pageable page, Usuario user);

	Optional<Disparador> findByIdAndUser(Long id, Usuario user);

	List<Disparador> findAllByStatus(DisparadorStatus status);

	List<Disparador> findAllByUserAndDtDisparoGreaterThanEqualAndDtDisparoLessThanEqual(Usuario user, LocalDateTime minusDays,
			LocalDateTime plusDays);
	
}
