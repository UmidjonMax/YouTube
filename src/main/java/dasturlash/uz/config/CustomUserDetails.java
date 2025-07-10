package dasturlash.uz.config;

import dasturlash.uz.enums.ProfileRoleEnum;
import dasturlash.uz.enums.ProfileStatusEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String id;
    private String email;
    private String password;
    private ProfileStatusEnum status;
    private List<SimpleGrantedAuthority> roles;

    public CustomUserDetails(String id, String email, String password, ProfileStatusEnum status,
                             List<ProfileRoleEnum> roleList) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;

        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roleList.forEach(role -> {
            roles.add(new SimpleGrantedAuthority(role.name()));
        });
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(ProfileStatusEnum.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return id;
    }
}
