package internet.com.security.userprincal;


import com.fasterxml.jackson.annotation.JsonIgnore;
import internet.com.entity.user.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> roles;

    public UserPrinciple() {
    }

    /**
     * Create by HoangHN
     * Date create: 08/09/2022
     * function: UserPrinciple
     * @param username
     * @param password
     * @param roles
     */
    public UserPrinciple(String username, String password, Collection<? extends GrantedAuthority> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    /**
     * Create by HoangHN
     * Date create: 08/09/2022
     * function: UserPrinciple
     * @param appUser
     * @return
     */
    public static UserPrinciple build(AppUser appUser){
        List<GrantedAuthority> authorities = appUser.getRoles().stream().map(role->
                new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new UserPrinciple(
                appUser.getUsername(),
                appUser.getPassword(),
                authorities
        );
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
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
