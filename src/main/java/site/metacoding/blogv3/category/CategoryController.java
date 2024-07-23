package site.metacoding.blogv3.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/s/category/writeForm")
    public String categoryForm() {

        return "/category/writeForm";
    }

    @PostMapping("/s/category")
    public String saveCategory(CategoryRequest.SaveDTO reqDTO) {

        categoryService.save(reqDTO.toEntity());

        return "redirect:/s/category/writeForm";
    }
}
