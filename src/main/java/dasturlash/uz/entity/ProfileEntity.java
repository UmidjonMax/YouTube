package dasturlash.uz.entity;

import dasturlash.uz.enums.ProfileStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email", nullable = false)
    private String email;
    private String password;
    private String photoUrl;
    @OneToMany(mappedBy = "profile")
    private List<ProfileRoleEntity> roleList;
    @Enumerated(EnumType.STRING)
    private ProfileStatusEnum status;
}
