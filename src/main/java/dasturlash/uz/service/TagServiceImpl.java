package dasturlash.uz.service;

import dasturlash.uz.dto.TagRequestDTO;
import dasturlash.uz.dto.TagResponseDTO;
import dasturlash.uz.entity.TagEntity;
import dasturlash.uz.mapper.TagMapper;
import dasturlash.uz.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public TagResponseDTO create(TagRequestDTO dto) {
        TagEntity entity = TagEntity.builder()
                .nameUz(dto.getNameUz())
                .nameRu(dto.getNameRu())
                .nameEn(dto.getNameEn())
                .build();

        tagRepository.save(entity);
        return TagMapper.toDTO(entity);
    }

    @Override
    public TagResponseDTO update(Integer id, TagRequestDTO dto) {
        TagEntity entity = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found"));

        TagMapper.update(entity, dto);
        tagRepository.save(entity);

        return TagMapper.toDTO(entity);
    }

    @Override
    public void delete(Integer id) {
        TagEntity entity = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found"));
        tagRepository.delete(entity);
    }

    @Override
    public List<TagResponseDTO> getAll() {
        return tagRepository.findAll().stream()
                .map(TagMapper::toDTO)
                .collect(Collectors.toList());
    }
}
