package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.registrationDTOs.RegistrationDTO;
import filipeProject.example.authenticationJwt.enums.Payment;
import filipeProject.example.authenticationJwt.exceptions.ConflictException;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.RegistrationRepository;
import filipeProject.example.authenticationJwt.repositories.TalkRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegistrationService {

    private final RegistrationRepository repository;
    private final TalkRepository talkRepository;

    public RegistrationService(RegistrationRepository repository, TalkRepository talkRepository) {
        this.repository = repository;
        this.talkRepository = talkRepository;
    }

    public RegistrationDTO getRegistration(Long registerId, JwtAuthenticationToken token){

        var register = repository.findById(registerId)
                .orElseThrow(()-> new ResourceNotFoundException("Inscrição não encontrada"));

        if(!register.getUser().getId().equals(UUID.fromString(token.getName()))){
            throw new ConflictException("O usuário logado não é o responsável pela inscrição");
        }

        return new RegistrationDTO(register);

    }

    @Transactional
    public void confirmRegister(Long registerId, JwtAuthenticationToken token){

        var register = repository.findById(registerId)
                .orElseThrow(()-> new ResourceNotFoundException("Inscrição não encontrada"));

        if(!register.getUser().getId().equals(UUID.fromString(token.getName()))){
            throw new ConflictException("O usuário logado não é o responsável pela inscrição");
        }

        if (register.getPaymentStatus() == Payment.CONFIRMED) {
            throw new ConflictException("Esta inscrição já foi confirmada");
        }

        var talk = register.getTalk();

        if (talk.getNumberAvailable() <= 0) {
            throw new ConflictException("O número de vagas disponíveis já esgotou");
        }

        register.setPaymentStatus(Payment.CONFIRMED);
        talk.setNumberAvailable(talk.getNumberAvailable() - 1);

        repository.save(register);
        talkRepository.save(talk);

    }


    public void unregisterTalk(Long registerId, JwtAuthenticationToken token){

        var register = repository.findById(registerId)
                .orElseThrow(()-> new ResourceNotFoundException("Inscrição não encontrada"));

        if(!register.getUser().getId().equals(UUID.fromString(token.getName()))){
            throw new ConflictException("O usuário logado não é o responsável pela inscrição");
        }

        if (register.getPaymentStatus() == Payment.CANCELLED) {
            throw new ConflictException("Esta inscrição já foi cancelada.");
        }

        register.setPaymentStatus(Payment.CANCELLED);
        repository.save(register);

    }



}
