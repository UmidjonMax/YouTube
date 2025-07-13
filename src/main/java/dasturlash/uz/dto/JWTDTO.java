package dasturlash.uz.dto;

import dasturlash.uz.enums.ProfileRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTDTO {
    private String email;
    private ProfileRoleEnum role;
}
