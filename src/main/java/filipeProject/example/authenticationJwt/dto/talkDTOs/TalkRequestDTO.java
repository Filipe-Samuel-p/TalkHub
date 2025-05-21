package filipeProject.example.authenticationJwt.dto.talkDTOs;

import filipeProject.example.authenticationJwt.entities.TalkRequest;
import filipeProject.example.authenticationJwt.enums.DifficultyTalkLevel;
import filipeProject.example.authenticationJwt.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TalkRequestDTO {

    private Long id;
    private String title;
    private String description;
    private Instant startTime;
    private Duration duration;
    private int totalCapacity;
    private int numberAvailable;
    private String local;
    private DifficultyTalkLevel difficultyLevel;
    private UUID speakerId;
    private Long eventId;
    private Long categoryId;
    private RequestStatus status;


    public TalkRequestDTO(TalkRequest entity){
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        startTime = entity.getStartTime();
        duration = entity.getDuration();
        totalCapacity = entity.getTotalCapacity();
        numberAvailable = entity.getNumberAvailable();
        local = entity.getLocal();
        difficultyLevel = entity.getDifficultyLevel();
        speakerId = entity.getSpeakerId();
        eventId = entity.getEventId();
        categoryId = entity.getCategoryId();
        status = entity.getStatus();

    }
}
