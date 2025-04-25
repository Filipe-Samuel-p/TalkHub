package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.userDTOs.UpdateUserDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UserProfileDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UserRegisterDTO;
import filipeProject.example.authenticationJwt.entities.User;
import filipeProject.example.authenticationJwt.enums.RoleName;
import filipeProject.example.authenticationJwt.exceptions.AccessDeniedException;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.RoleRepository;
import filipeProject.example.authenticationJwt.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    public UpdateUserDTO updateUser (UpdateUserDTO dto, JwtAuthenticationToken token){

        var roles = token.getAuthorities()
                .stream()
                .map(Object::toString)
                .toList();


        boolean isAdmin = roles.contains("ROLE_ADMIN");
        UUID userId = UUID.fromString(token.getName());

        var user = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        if (!isAdmin && !user.getId().equals(userId)) {
            throw new AccessDeniedException("Acesso negado");
        }

        if(dto.getName() != null){
            user.setName(dto.getName());
        }

        if(dto.getEmail() != null){
            user.setEmail(dto.getEmail());

        } if(dto.getBiography() != null){
            user.setBiography(dto.getBiography());
        }

        if(dto.getImgUrl() != null){
            user.setImgUrl(dto.getImgUrl());
        }

        if(dto.getImgBackground() != null){
            user.setImgBackground(dto.getImgBackground());
        }

        user = repository.save(user);

        return new UpdateUserDTO(user);

    }



    private void dtoRegisterToEntity(UserRegisterDTO dto, User entity){
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        entity.setCpf(dto.getCpf());
        entity.setBiography(dto.getBiography());
    }


}
