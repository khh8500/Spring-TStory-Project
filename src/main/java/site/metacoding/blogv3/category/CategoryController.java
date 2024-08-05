package site.metacoding.blogv3.category;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import site.metacoding.blogv3.user.User;

@RequiredArgsConstructor
@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final HttpSession session;

    @GetMapping("/s/category/writeForm")
    public String categoryForm() {

        return "/category/writeForm";
    }

    @PostMapping("/s/category")
    public String saveCategory(CategoryRequest.SaveDTO reqDTO) {

        System.out.println("reqDTO = " + reqDTO);
        Integer sessionUserId = (Integer) session.getAttribute("sessionUserId");
        System.out.println("sessionUserId444444 = " + sessionUserId);
        Category category = reqDTO.toEntity(sessionUserId);
        System.out.println("sessionUserId555555 = " + sessionUserId);
        categoryService.save(reqDTO, sessionUserId);
        System.out.println("sessionUserId66666 = " + sessionUserId);
        return "redirect:/s/category/writeForm";
    }
}
