package filipeProject.example.authenticationJwt.dto.speakerDTOs;


import filipeProject.example.authenticationJwt.dto.userDTOs.UserDTO;
import filipeProject.example.authenticationJwt.entities.Speaker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SpeakerDTO {
    private String resume;
    private String specialties;
    private String institution;
    private UserDTO user;

    public SpeakerDTO(Speaker speaker){
        resume = speaker.getResume();
        specialties = speaker.getSpecialties();
        institution = speaker.getInstitution();
        user = new UserDTO(speaker.getUser());
    }

}
