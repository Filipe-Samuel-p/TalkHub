package filipeProject.example.authenticationJwt.entities;


import filipeProject.example.authenticationJwt.enums.SpeakerRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_speaker_Request")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpeakerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private User user;

    private Instant requestDate;

    @Enumerated(EnumType.STRING)
    private SpeakerRequestStatus status;

}
