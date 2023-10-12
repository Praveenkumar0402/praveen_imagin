package reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import reservation.enums.Gender;
import reservation.enums.UserStatus;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "id", initialValue = 100, allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "email")
    private String email;

    private String password;

    @Column(name = "mobile")
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(name = "userstatus")
    private UserStatus userstatus;

    private String roles;


    public User(int id, String firstname, String lastname, Gender gender, String email, String password, String mobile, UserStatus userstatus, String roles) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.userstatus = userstatus;
        this.roles = roles;
    }

    public User(User user) {
        id = user.getId();
        firstname = user.getFirstname();
        lastname = user.getLastname();
        gender = user.getGender();
        email = user.getEmail();
        password = user.getPassword();
        mobile = user.getMobile();
        userstatus = user.getUserstatus();
        roles = user.getRoles();
    }
}