package internet.com.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 * Create by HoangHN
 * Date create: 08/09/2022
 * function: AppUser
 * @param
 * @return
 */
@Entity
@Table(name = "user")
public class AppUser {
    @Id
    @Column(name = "user_name")
    private String username;
    @Column(name = "user_password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_name"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<AppRole> appRoles = new HashSet<>();

    public AppUser() {
    }

    public AppUser(String username, String password, Set<AppRole> appRoles) {
        this.username = username;
        this.password = password;
        this.appRoles = appRoles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AppRole> getRoles() {
        return appRoles;
    }

    public void setRoles(Set<AppRole> appRoles) {
        this.appRoles = appRoles;
    }
}
