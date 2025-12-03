package filipeProject.example.authenticationJwt.service;


import filipeProject.example.authenticationJwt.dto.AIDTOs.AIQuestionRequest;
import filipeProject.example.authenticationJwt.dto.AIDTOs.AIQuestionResponse;
import filipeProject.example.authenticationJwt.entities.Event;
import filipeProject.example.authenticationJwt.entities.Talk;
import filipeProject.example.authenticationJwt.repositories.EventRepository;
import filipeProject.example.authenticationJwt.repositories.TalkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AIAssistantService {

    private final ChatModel chatModel;
    private final ContextBuilderService contextBuilderService;
    private final TalkRepository talkRepository;
    private final EventRepository eventRepository;

    public AIAssistantService(ChatModel chatModel, ContextBuilderService contextBuilderService, TalkRepository talkRepository, EventRepository eventRepository) {
        this.chatModel = chatModel;
        this.contextBuilderService = contextBuilderService;
        this.talkRepository = talkRepository;
        this.eventRepository = eventRepository;
    }


    public AIQuestionResponse processQuestion(AIQuestionRequest request) {
        long startTime = System.currentTimeMillis();

        try {
            log.info("Processando pergunta: {}", request.getQuestion());


            String context = contextBuilderService.buildContext(
                    request.getCategoryId(),
                    request.getEventId()
            );

            SystemMessage systemMessage = new SystemMessage(context);
            UserMessage userMessage = new UserMessage(request.getQuestion());
            Prompt prompt = new Prompt(List.of(systemMessage, userMessage));


            log.info("Enviando requisição para a IA...");
            String aiResponse = chatModel.call(prompt).getResult().getOutput().getText();
            log.info("Resposta da IA recebida: {}", aiResponse.substring(0, Math.min(100, aiResponse.length())));


            List<Long> talkIds = extractIds(aiResponse, "ID: (\\d+)");
            List<Long> eventIds = extractIds(aiResponse, "Evento ID: (\\d+)");


            List<AIQuestionResponse.TalkSummary> relatedTalks = getTalkSummaries(talkIds);
            List<AIQuestionResponse.EventSummary> relatedEvents = getEventSummaries(eventIds);

            long processingTime = System.currentTimeMillis() - startTime;


            return AIQuestionResponse.builder()
                    .question(request.getQuestion())
                    .answer(aiResponse)
                    .relatedTalks(relatedTalks)
                    .relatedEvents(relatedEvents)
                    .processingTimeMs(processingTime)
                    .build();

        } catch (Exception e) {
            log.error("Erro ao processar pergunta: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao processar pergunta com a IA: " + e.getMessage(), e);
        }
    }

    private List<Long> extractIds(String text, String pattern) {
        List<Long> ids = new ArrayList<>();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);

        while (m.find()) {
            try {
                ids.add(Long.parseLong(m.group(1)));
            } catch (NumberFormatException e) {
                log.warn("Erro ao parsear ID: {}", m.group(1));
            }
        }

        return ids;
    }


    private List<AIQuestionResponse.TalkSummary> getTalkSummaries(List<Long> talkIds) {
        if (talkIds.isEmpty()) {
            return List.of();
        }

        return talkRepository.findAllById(talkIds).stream()
                .map(this::convertToTalkSummary)
                .collect(Collectors.toList());
    }

    private List<AIQuestionResponse.EventSummary> getEventSummaries(List<Long> eventIds) {
        if (eventIds.isEmpty()) {
            return List.of();
        }

        return eventRepository.findAllById(eventIds).stream()
                .map(this::convertToEventSummary)
                .collect(Collectors.toList());
    }


    private AIQuestionResponse.TalkSummary convertToTalkSummary(Talk talk) {
        return AIQuestionResponse.TalkSummary.builder()
                .id(talk.getId())
                .title(talk.getTitle())
                .description(talk.getDescription())
                .speakerName(talk.getSpeaker() != null ? talk.getSpeaker().getUser().getName() : "Não informado")
                .category(talk.getCategory() != null ? talk.getCategory().getName() : "Sem categoria")
                .eventName(talk.getEvent() != null ? talk.getEvent().getName() : "Sem evento")
                .build();
    }


    private AIQuestionResponse.EventSummary convertToEventSummary(Event event) {
        return AIQuestionResponse.EventSummary.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .startDate(event.getStartDate() != null ? event.getStartDate().toString() : null)
                .endDate(event.getEndDate() != null ? event.getEndDate().toString() : null)
                .build();
    }
}