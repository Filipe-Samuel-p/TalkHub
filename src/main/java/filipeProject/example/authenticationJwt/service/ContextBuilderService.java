package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.entities.Category;
import filipeProject.example.authenticationJwt.entities.Event;
import filipeProject.example.authenticationJwt.entities.Talk;
import filipeProject.example.authenticationJwt.repositories.CategoryRepository;
import filipeProject.example.authenticationJwt.repositories.EventRepository;
import filipeProject.example.authenticationJwt.repositories.TalkRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ContextBuilderService {

    private final TalkRepository talkRepository;
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

    public ContextBuilderService(TalkRepository talkRepository, EventRepository eventRepository, CategoryRepository categoryRepository) {
        this.talkRepository = talkRepository;
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Constrói o contexto completo para enviar à IA
     */
    public String buildContext(Long categoryId, Long eventId) {
        StringBuilder context = new StringBuilder();

        context.append("=== CONTEXTO DO TALKHUB ===\n\n");
        context.append("Você é um assistente inteligente do TalkHub, uma plataforma de gerenciamento de palestras e eventos.\n");
        context.append("Sua função é ajudar usuários a encontrar palestras, eventos e informações relevantes.\n\n");

        // Adiciona palestras
        context.append("### PALESTRAS DISPONÍVEIS:\n");
        List<Talk> talks = getTalks(categoryId, eventId);
        for (Talk talk : talks) {
            context.append(formatTalk(talk));
        }

        // Adiciona eventos
        context.append("\n### EVENTOS PRÓXIMOS:\n");
        List<Event> events = getUpcomingEvents();
        for (Event event : events) {
            context.append(formatEvent(event));
        }

        // Adiciona categorias
        context.append("\n### CATEGORIAS DISPONÍVEIS:\n");
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            context.append(String.format("- %s (ID: %d)\n", category.getName(), category.getId()));
        }

        context.append("\n=== INSTRUÇÕES ===\n");
        context.append("- Responda em português de forma amigável e profissional\n");
        context.append("- Mencione os IDs das palestras e eventos relevantes\n");
        context.append("- Se não souber algo, seja honesto\n");
        context.append("- Forneça informações úteis e diretas\n\n");

        return context.toString();
    }


    private List<Talk> getTalks(Long categoryId, Long eventId) {
        if (categoryId != null && eventId != null) {
            return talkRepository.findByCategoryIdAndEventId(categoryId, eventId, PageRequest.of(0, 15));
        } else if (categoryId != null) {
            return talkRepository.findByCategoryId(categoryId, PageRequest.of(0, 15));
        } else if (eventId != null) {
            return talkRepository.findByEventId(eventId, PageRequest.of(0, 15));
        } else {
            return talkRepository.findTop20ByOrderByIdDesc();
        }
    }

    private List<Event> getUpcomingEvents() {
        return eventRepository.findUpcomingEvents(Instant.now(), PageRequest.of(0, 10));
    }


    private String formatTalk(Talk talk) {
        return String.format(
                "ID: %d | Título: %s | Descrição: %s | Palestrante: %s | Categoria: %s | Evento: %s\n",
                talk.getId(),
                talk.getTitle(),
                talk.getDescription() != null ? talk.getDescription().substring(0, Math.min(100, talk.getDescription().length())) + "..." : "Sem descrição",
                talk.getSpeaker() != null ? talk.getSpeaker().getUser().getName() : "Não informado",
                talk.getCategory() != null ? talk.getCategory().getName() : "Sem categoria",
                talk.getEvent() != null ? talk.getEvent().getName() : "Sem evento"
        );
    }

    private String formatEvent(Event event) {
        return String.format(
                "ID: %d | Nome: %s | Descrição: %s | Data: %s até %s\n",
                event.getId(),
                event.getName(),
                event.getDescription() != null ? event.getDescription().substring(0, Math.min(100, event.getDescription().length())) + "..." : "Sem descrição",
                event.getStartDate(),
                event.getEndDate()
        );
    }
}