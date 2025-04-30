package filipeProject.example.authenticationJwt.dto.categoryDTOs;


import filipeProject.example.authenticationJwt.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CategoryDTO {
    private Long id;
    private String name;

    public CategoryDTO(Category category){
        id = category.getId();
        name = category.getName();
    }
}
