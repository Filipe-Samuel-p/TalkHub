package filipeProject.example.authenticationJwt.entities;

import filipeProject.example.authenticationJwt.dto.loginDTOs.LoginRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.*;

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

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
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
    private List<User> following = new ArrayList<>();

    @ManyToMany(mappedBy = "following")
    private List<User> followers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Registration> registrations = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tb_user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    public Boolean isLoginCorrect(LoginRequestDTO loginRequestDTO, PasswordEncoder password){
        return password.matches(loginRequestDTO.password(),this.password);
    }
}
