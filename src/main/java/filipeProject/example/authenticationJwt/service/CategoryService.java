package filipeProject.example.authenticationJwt.service;

import filipeProject.example.authenticationJwt.dto.categoryDTOs.CategoryAndTalksDTO;
import filipeProject.example.authenticationJwt.dto.categoryDTOs.CategoryDTO;
import filipeProject.example.authenticationJwt.entities.Category;
import filipeProject.example.authenticationJwt.exceptions.DataIntegrityViolationException;
import filipeProject.example.authenticationJwt.exceptions.ResourceNotFoundException;
import filipeProject.example.authenticationJwt.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }


    public CategoryDTO createCategory(CategoryDTO dto){

        var entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);

    }

    public List<CategoryDTO> allCategories (){
        var list = repository.findAll();
        return list.stream().map(CategoryDTO::new).toList();
    }

    public CategoryAndTalksDTO categoryAndTalks (Long id){

        var category = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Categoria não encontrada"));

        return new CategoryAndTalksDTO(category);

    }

    public void deleteCategory(Long id){

        var isCategoryExist = repository.existsById(id);
        if(!isCategoryExist){
            throw new ResourceNotFoundException("Categoria não encontrada");
        }

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Falha de integridade referencial");
        }
    }

}
