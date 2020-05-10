package livemarket.business.b2bcart.repositories;


import livemarket.business.b2bcart.models.items.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {


}
