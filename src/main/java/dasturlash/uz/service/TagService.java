package dasturlash.uz.service;


import dasturlash.uz.dto.TagRequestDTO;
import dasturlash.uz.dto.TagResponseDTO;

import java.util.List;

public interface TagService {
    TagResponseDTO create(TagRequestDTO dto);

    TagResponseDTO update(Integer id, TagRequestDTO dto);

    void delete(Integer id);

    List<TagResponseDTO> getAll();
}
