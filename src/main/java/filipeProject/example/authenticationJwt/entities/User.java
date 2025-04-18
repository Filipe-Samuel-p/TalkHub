package filipeProject.example.authenticationJwt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String cpf;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant creationDate;

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

    public void setCreationDateNow() {
        this.creationDate = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }
}
