package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "email_history")
public class EmailHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "to_account")
    private String toAccount;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Column(name = "attempt_count")
    private Integer attemptCount = 0;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;
}
