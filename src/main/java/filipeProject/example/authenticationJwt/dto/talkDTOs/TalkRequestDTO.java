package filipeProject.example.authenticationJwt.dto.talkDTOs;

import filipeProject.example.authenticationJwt.entities.Category;
import filipeProject.example.authenticationJwt.entities.Talk;
import filipeProject.example.authenticationJwt.enums.DifficultyTalkLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TalkRequestDDTO {

    private Long id;
    private String title;
    private String description;
    private Instant startTime;
    private Duration duration;
    private int totalCapacity;
    private int numberAvailable;
    private String local;
    private DifficultyTalkLevel difficultyLevel;
    private Long eventId;
    private Set<Long> categoryIds;



    public TalkRequestDDTO(Talk entity){
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        startTime = entity.getStartTime();
        duration = entity.getDuration();
        totalCapacity = entity.getTotalCapacity();
        numberAvailable = entity.getNumberAvailable();
        local = entity.getLocal();
        difficultyLevel = entity.getDifficultyLevel();
        eventId = entity.getEvent().getId();
        categoryIds = entity.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet());

    }
}
