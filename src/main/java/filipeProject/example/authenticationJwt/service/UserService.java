package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.UserProfileDTO;
import filipeProject.example.authenticationJwt.dto.UserRegisterDTO;
import filipeProject.example.authenticationJwt.entities.Role;
import filipeProject.example.authenticationJwt.entities.User;
import filipeProject.example.authenticationJwt.enums.RoleName;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.RoleRepository;
import filipeProject.example.authenticationJwt.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserRegisterDTO newUser(UserRegisterDTO dto){

        var userEntity = new User();
        dtoRegisterToEntity(dto,userEntity);
        userEntity.getRoles().add(roleRepository.findByAuthority(RoleName.ROLE_USER));
        userEntity.setCreationDate(Instant.now());
        userEntity = repository.save(userEntity);
        return new UserRegisterDTO(userEntity);
    }

    public UserProfileDTO userProfile(UUID id){

        var userProfile = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return new UserProfileDTO(userProfile);

    }



    private void dtoRegisterToEntity(UserRegisterDTO dto, User entity){
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        entity.setCpf(dto.getCpf());
        entity.setBiography(dto.getBiography());
    }

}
