package dasturlash.uz.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    private String id;
    private String name;
    private String email;
    private String surname;
    private String photoUrl;
}
