package filipeProject.example.authenticationJwt.dto.categoryDTOs;


import filipeProject.example.authenticationJwt.dto.talkDTOs.TalkDTO;
import filipeProject.example.authenticationJwt.entities.Category;
import filipeProject.example.authenticationJwt.entities.Talk;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryAndTalksDTO {

    private Long id;
    private String name;
    private Set<TalkDTO> talks = new HashSet<>();

    public CategoryAndTalksDTO(Category category){
        id = category.getId();
        name = category.getName();
        talks = category.getTalks().stream().map(TalkDTO::new).collect(Collectors.toSet());
    }

}
