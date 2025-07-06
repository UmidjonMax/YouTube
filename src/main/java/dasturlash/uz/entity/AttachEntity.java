package dasturlash.uz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class AttachEntity {
    @Id
    private String id;
    private String originalName;
    private String extension;
    private Long size;
    private String path;
    private String contentType;
    private LocalDateTime createdDate;
}
