package filipeProject.example.authenticationJwt.dto.registrationDTOs;


import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkDTO;
import filipeProject.example.authenticationJwt.entities.Registration;
import filipeProject.example.authenticationJwt.entities.Talk;
import filipeProject.example.authenticationJwt.entities.User;
import filipeProject.example.authenticationJwt.enums.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistrationDTO {

    private Long id;
    private Instant registrationDate;
    private Payment paymentStatus;
    private TalkDTO talk;

    public RegistrationDTO (Registration entity){
        id = entity.getId();
        registrationDate = entity.getRegistrationDate();
        paymentStatus = entity.getPaymentStatus();
        talk = new TalkDTO(entity.getTalk());
    }

}
