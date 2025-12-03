package filipeProject.example.authenticationJwt.dto.AIDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIQuestionRequest {

    @NotBlank(message = "A pergunta não pode estar vazia")
    private String question;

    private Long categoryId;
    private Long eventId;
}
