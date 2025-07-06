package youtube_step_by_step.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDTO {

    private Integer id;

    private String name;

    private LocalDateTime createdDate;
}
