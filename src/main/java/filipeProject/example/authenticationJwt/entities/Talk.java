package filipeProject.example.authenticationJwt.entities;

import filipeProject.example.authenticationJwt.enums.DifficultyTalkLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;
import java.util.List;


@Entity
@Table(name = "tb_talk")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Instant startTime;


    private Duration duration;
    private int totalCapacity;
    private int numberAvailable;
    private String local;

    @Enumerated(EnumType.STRING)
    private DifficultyTalkLevel difficultyLevel;

    @OneToMany(mappedBy = "talk")
    private List<Registration> registrations;

    @ManyToOne
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Talk(TalkRequest talkRequest){

            title = talkRequest.getTitle();
            description = talkRequest.getDescription();
            startTime = talkRequest.getStartTime();
            duration = talkRequest.getDuration();
            totalCapacity = talkRequest.getTotalCapacity();
            numberAvailable = talkRequest.getNumberAvailable();
            local = talkRequest.getLocal();
            difficultyLevel = talkRequest.getDifficultyLevel();

    }

}
