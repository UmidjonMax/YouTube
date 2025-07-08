package dasturlash.uz.repository;

import dasturlash.uz.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {

}
