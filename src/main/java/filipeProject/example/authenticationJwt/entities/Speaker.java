package filipeProject.example.authenticationJwt.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_speaker")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Speaker {

    @Id
    @Column(name = "user_id")
    private Long id;

    private String resume;
    private String specialties;
    private String institution;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "speaker")
    private List<Talk> talks;

}
