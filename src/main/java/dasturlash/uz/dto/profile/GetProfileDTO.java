package dasturlash.uz.dto.profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProfileDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String photoUrl;
}
