package dasturlash.uz.entity;

import dasturlash.uz.enums.ProfileRoleEnum;
import dasturlash.uz.enums.ProfileStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @Enumerated(EnumType.STRING)
    private ProfileRoleEnum role;
    @Enumerated(EnumType.STRING)
    private ProfileStatusEnum status;
}
