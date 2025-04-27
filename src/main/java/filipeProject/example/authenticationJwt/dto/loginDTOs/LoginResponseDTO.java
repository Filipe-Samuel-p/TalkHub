package filipeProject.example.authenticationJwt.dto.loginDTOs;

public record LoginResponseDTO(String accessToken, Long expiresIn) {
}
