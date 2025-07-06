package youtube_step_by_step.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youtube_step_by_step.dto.CategoryDTO;
import youtube_step_by_step.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;


    public CategoryDTO create(CategoryDTO dto) {


        return null;
    }


}
