package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tag")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameUz;
    private String nameRu;
    private String nameEn;

    private Boolean visible = true;

    private LocalDateTime createdDate = LocalDateTime.now();
}