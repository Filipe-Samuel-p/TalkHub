package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.UserRegisterDTO;
import filipeProject.example.authenticationJwt.entities.Role;
import filipeProject.example.authenticationJwt.entities.User;
import filipeProject.example.authenticationJwt.enums.RoleName;
import filipeProject.example.authenticationJwt.repositories.RoleRepository;
import filipeProject.example.authenticationJwt.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

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


    private void dtoRegisterToEntity(UserRegisterDTO dto, User entity){
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        entity.setCpf(dto.getCpf());
        entity.setBiography(dto.getBiography());
    }

}
