package dasturlash.uz.dto.auth;

import dasturlash.uz.enums.ProfileRoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponseDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String photoUrl;
    private List<ProfileRoleEnum> roles;
    private String jwt;
}
