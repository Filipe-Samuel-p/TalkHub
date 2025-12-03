package filipeProject.example.authenticationJwt.dto.talkDTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import filipeProject.example.authenticationJwt.dto.categoryDTOs.CategoryDTO;
import filipeProject.example.authenticationJwt.dto.eventDTOs.EventDTO;
import filipeProject.example.authenticationJwt.dto.speakerDTOs.SpeakerDTO;
import filipeProject.example.authenticationJwt.entities.*;
import filipeProject.example.authenticationJwt.enums.DifficultyTalkLevel;
import filipeProject.example.authenticationJwt.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TalkCreationDTO {

    private Long id;
    private String title;
    private String description;
    private Instant startTime;
    private Duration duration;
    private int totalCapacity;
    private int numberAvailable;
    private String local;
    private DifficultyTalkLevel difficultyLevel;
    private SpeakerDTO speaker;
    private EventDTO event;
    private RequestStatus approvalStatus;

    @JsonIgnore
    private Instant request_response_date;



    public TalkCreationDTO(Talk entity){
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        startTime = entity.getStartTime();
        duration = entity.getDuration();
        totalCapacity = entity.getTotalCapacity();
        numberAvailable = entity.getNumberAvailable();
        local = entity.getLocal();
        difficultyLevel = entity.getDifficultyLevel();
        speaker = new SpeakerDTO(entity.getSpeaker());
        event = new EventDTO(entity.getEvent());
        approvalStatus = entity.getApprovalStatus();
        request_response_date = entity.getRequest_response_date();

    }
}
