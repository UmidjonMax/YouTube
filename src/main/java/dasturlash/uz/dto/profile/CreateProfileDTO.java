package dasturlash.uz.dto.profile;

import dasturlash.uz.enums.ProfileRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProfileDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private ProfileRoleEnum role;
}
