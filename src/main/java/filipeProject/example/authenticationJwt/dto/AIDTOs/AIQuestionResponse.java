package filipeProject.example.authenticationJwt.dto.AIDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIQuestionResponse {

    private String question;
    private String answer;
    private List<TalkSummary> relatedTalks;
    private List<EventSummary> relatedEvents;
    private Long processingTimeMs;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TalkSummary {
        private Long id;
        private String title;
        private String description;
        private String speakerName;
        private String category;
        private String eventName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventSummary {
        private Long id;
        private String name;
        private String description;
        private String startDate;
        private String endDate;
    }
}