package filipeProject.example.authenticationJwt.entities;

import filipeProject.example.authenticationJwt.enums.DifficultyLevel;
import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "tb_talk")
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String startTime;
    private String duration;
    private int totalCapacity;
    private int numberAvailable;
    private String local;
    private Enum<DifficultyLevel> difficultyLevel;

    @OneToMany(mappedBy = "talk")
    private List<Registrations> registrations;

    @ManyToOne
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;



}
