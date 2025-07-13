package dasturlash.uz.mapper;

import dasturlash.uz.dto.TagRequestDTO;
import dasturlash.uz.dto.TagResponseDTO;
import dasturlash.uz.entity.TagEntity;

public class TagMapper {
    public static TagResponseDTO toDTO(TagEntity entity) {
        return TagResponseDTO.builder()
                .id(entity.getId())
                .nameUz(entity.getNameUz())
                .nameRu(entity.getNameRu())
                .nameEn(entity.getNameEn())
                .createdDate(entity.getCreatedDate())
                .build();
    }

    public static void update(TagEntity entity, TagRequestDTO dto) {
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
    }
}

