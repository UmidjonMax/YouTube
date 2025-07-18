package dasturlash.uz.entity;

import dasturlash.uz.enums.ProfileRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profile_role")
public class ProfileRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profile_id")
    private String profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false, nullable = false)
    private ProfileEntity profile;

    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    private ProfileRoleEnum roles;
}
