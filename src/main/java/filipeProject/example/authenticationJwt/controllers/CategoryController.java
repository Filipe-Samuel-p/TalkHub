package filipeProject.example.authenticationJwt.controllers;

import filipeProject.example.authenticationJwt.dto.categoryDTOs.CategoryAndTalksDTO;
import filipeProject.example.authenticationJwt.dto.categoryDTOs.CategoryDTO;
import filipeProject.example.authenticationJwt.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO dto){

        var entity = service.createCategory(dto);
        return ResponseEntity.ok(entity);

    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<CategoryDTO>> allCategories(){
        var list = service.allCategories();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}/talks")
    public ResponseEntity<CategoryAndTalksDTO> categoryAndTalks(@PathVariable Long id){
        var dto = service.categoryAndTalks(id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
