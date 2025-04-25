package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.userDTOs.UpdateUserDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UserProfileDTO;
import filipeProject.example.authenticationJwt.dto.userDTOs.UserRegisterDTO;
import filipeProject.example.authenticationJwt.entities.User;
import filipeProject.example.authenticationJwt.enums.RoleName;
import filipeProject.example.authenticationJwt.exceptions.AccessDeniedException;
import filipeProject.example.authenticationJwt.exceptions.ConflictException;
import filipeProject.example.authenticationJwt.exceptions.DataIntegrityViolationException;
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

    public void deleteUser(UUID id){

        boolean userExist = repository.existsById(id);
        if(!userExist){
            throw new ResourceNotFoundException("Usuário não encontrado");
        }

        try{
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Falha de integridade referencial");
        }

    }

    public void addNewFollower(UUID userToFollowId, JwtAuthenticationToken token){

        var userToFollow = repository.findById(userToFollowId)
                .orElseThrow(()-> new ResourceNotFoundException("Seguidor não encontrado"));

        var follower = repository.findById(UUID.fromString(token.getName())) // usuário logado
                .orElseThrow(()-> new ResourceNotFoundException("Usuário principal não encontrado"));

        var isFollow = follower.getFollowing().contains(userToFollow);
        var followYourSelf = userToFollow.getId().equals(follower.getId());

        if (followYourSelf || isFollow) {
            throw new ConflictException("Você não pode seguir a si mesmo ou você ja esta seguindo este usuário.");
        }

        follower.getFollowing().add(userToFollow);
        follower.setNumFollowing(follower.getFollowing().size());

        userToFollow.getFollowers().add(follower);
        userToFollow.setNumFollowers(userToFollow.getFollowers().size());

        repository.save(userToFollow);
        repository.save(follower);

    }

    public void deleteFollower(UUID userToUnfollowId, JwtAuthenticationToken token){

        var userToUnfollow = repository.findById(userToUnfollowId)
                .orElseThrow(()-> new ResourceNotFoundException("Usuário que quer deixar de seguir não encontrado"));

        var follower = repository.findById(UUID.fromString(token.getName())) // usuário logado
                .orElseThrow(()-> new ResourceNotFoundException("Usuário principal não encontrado"));

        var isFollow = follower.getFollowing().contains(userToUnfollow);
        var unfollowYourSelf = userToUnfollow.getId().equals(follower.getId());

        if(!isFollow || unfollowYourSelf){
            throw new ConflictException(" Usuário principal não esta seguindo o outro usuário ou esta querendo deixar de seguir a si mesmo");
        }

        follower.getFollowing().remove(userToUnfollow);
        follower.setNumFollowing(follower.getFollowing().size()); // não precisará checar números negativos pq esta sendo atualizado com o tamanho da lista.

        userToUnfollow.getFollowers().remove(follower);
        userToUnfollow.setNumFollowers(userToUnfollow.getFollowers().size());

        repository.save(follower);
        repository.save(userToUnfollow);

    }



    private void dtoRegisterToEntity(UserRegisterDTO dto, User entity){
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        entity.setCpf(dto.getCpf());
        entity.setBiography(dto.getBiography());
    }


}
