package youtube_step_by_step.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import youtube_step_by_step.entity.CategoryEntity;
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {

}
