package site.metacoding.blogv3.post;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import site.metacoding.blogv3.category.Category;
import site.metacoding.blogv3.user.User;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final HttpSession session;

    @GetMapping("/post")
    public String detail() {

        return "/post/detail";
    }

    @GetMapping("/s/post/write-form")
    public String writeForm() {

        return "/post/writeForm";
    }

    @GetMapping("/user/post")
    public String postList() {

        return "/post/list";
    }

    @PostMapping("/s/post/save")
    public String save(PostRequest.SaveDTO reqDTO) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        postService.save(sessionUser.getId(), reqDTO);
        return "redirect:/user/post";
    }
}
