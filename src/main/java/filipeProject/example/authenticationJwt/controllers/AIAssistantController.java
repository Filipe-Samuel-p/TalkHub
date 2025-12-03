package filipeProject.example.authenticationJwt.controllers;


import filipeProject.example.authenticationJwt.dto.AIDTOs.AIQuestionRequest;
import filipeProject.example.authenticationJwt.dto.AIDTOs.AIQuestionResponse;
import filipeProject.example.authenticationJwt.service.AIAssistantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
@Tag(name = "AI Assistant", description = "Endpoints para assistente virtual com IA")
public class AIAssistantController {

    @Autowired
    private final AIAssistantService aiAssistantService;

    @PostMapping("/chat")
    @PreAuthorize("hasRole('USER') or hasRole('SPEAKER') or hasRole('ADMIN')")
    public ResponseEntity<AIQuestionResponse> askQuestion(@Valid @RequestBody AIQuestionRequest request) {
        log.info("Recebida pergunta: {}", request.getQuestion());

        AIQuestionResponse response = aiAssistantService.processQuestion(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("AI Assistant está funcionando! ✅");
    }
}