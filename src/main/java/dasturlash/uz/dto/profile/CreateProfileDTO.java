package dasturlash.uz.dto.profile;

import dasturlash.uz.entity.ProfileRoleEntity;
import dasturlash.uz.enums.ProfileRoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateProfileDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private List<ProfileRoleEnum> roles;
}
