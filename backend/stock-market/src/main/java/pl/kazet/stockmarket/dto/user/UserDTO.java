package pl.kazet.stockmarket.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kazet.stockmarket.entity.tables.User;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class UserDTO {
    private Long idUser;
    private String email;
    private String password;
    private String role;
    private Boolean nonExpired;
    private Boolean nonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;

    public UserDTO(User user) {
        this.idUser = user.getIdUser();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.nonExpired = user.getNonExpired();
        this.nonLocked = user.getNonLocked();
        this.credentialsNonExpired = user.getCredentialsNonExpired();
        this.enabled = user.getEnabled();
    }

    public User toEntity() {
        return new User(idUser, email, password, role, nonExpired, nonLocked, credentialsNonExpired, enabled);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "idUser=" + idUser +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", nonExpired=" + nonExpired +
                ", nonLocked=" + nonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                '}';
    }
}