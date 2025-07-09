package dasturlash.uz.service;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDTO create(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.getName());
        entity.setCreatedDate(LocalDateTime.now());
        repository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDTO update(Integer id, CategoryDTO dto) {
        Optional<CategoryEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        CategoryEntity entity = optional.get();
        entity.setName(dto.getName());
        repository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<CategoryDTO> getAll() {
        Iterable<CategoryEntity> all = repository.findAll();
        List<CategoryDTO> list = new ArrayList<>();
        for (CategoryEntity entity : all) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;
    }


}
