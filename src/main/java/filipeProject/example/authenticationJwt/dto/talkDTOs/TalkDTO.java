package filipeProject.example.authenticationJwt.dto.talkDTOs;

import filipeProject.example.authenticationJwt.dto.speakerDTOs.SpeakerDTO;
import filipeProject.example.authenticationJwt.entities.Speaker;
import filipeProject.example.authenticationJwt.entities.Talk;
import filipeProject.example.authenticationJwt.enums.DifficultyTalkLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TalkDTO {

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

    public TalkDTO(Talk entity){
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
    }

}
