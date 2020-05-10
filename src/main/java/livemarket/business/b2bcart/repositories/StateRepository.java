package livemarket.business.b2bcart.repositories;



import livemarket.business.b2bcart.models.rules.State;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StateRepository extends PagingAndSortingRepository<State, Long> {
    Optional<State> findById(Long id);
}
