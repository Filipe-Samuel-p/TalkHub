package filipeProject.example.authenticationJwt.dto;

public record LoginResponseDTO(String accessToken, Long expiresIn) {
}
