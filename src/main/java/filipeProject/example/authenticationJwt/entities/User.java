package filipeProject.example.authenticationJwt.entities;

import filipeProject.example.authenticationJwt.dto.LoginRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String email;
    private String password;
    private String cpf;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant creationDate;

    @Column(columnDefinition = "TEXT")
    private String biography;

    private String imgUrl;
    private String imgBackground;
    private int numFollowers;
    private int numFollowing;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Speaker speaker;

    @ManyToMany
    @JoinTable(name = "tb_user_following",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "following_id"))
    private List<User> following;

    @ManyToMany(mappedBy = "following")
    private List<User> followers;

    @OneToMany(mappedBy = "user")
    private List<Registrations> registrations;

    @ManyToMany
    @JoinTable(name = "tb_user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Boolean isLoginCorrect(LoginRequestDTO loginRequestDTO, PasswordEncoder password){
        return password.matches(loginRequestDTO.password(),this.password);
    }
}
