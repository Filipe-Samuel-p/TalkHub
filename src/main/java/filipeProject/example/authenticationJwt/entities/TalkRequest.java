package filipeProject.example.authenticationJwt.entities;


import filipeProject.example.authenticationJwt.enums.DifficultyTalkLevel;
import filipeProject.example.authenticationJwt.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_talk_request")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TalkRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Instant startTime;
    private Duration duration;
    private int totalCapacity;
    private int numberAvailable;
    private String local;

    @Enumerated(EnumType.STRING)
    private DifficultyTalkLevel difficultyLevel;
    private UUID speakerId;
    private Long eventId;
    private Long categoryId;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

}
