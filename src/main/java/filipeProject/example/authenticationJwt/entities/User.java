package filipeProject.example.authenticationJwt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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

    private boolean activeUser;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Speaker speaker;

    public void setCreationDateNow() {
        this.creationDate = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }
}
