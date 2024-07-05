package site.metacoding.blogv3.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    // 회원가입 폼
    @GetMapping("/join-form")
    public String joinForm() {

        return "/user/joinForm";
    }

    // 회원가입
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO) {

        UserResponse.JoinDTO sessionUser = userService.join(reqDTO);
        session.setAttribute("sessionUser", sessionUser);

        return "redirect:/";
    }

    // 로그인 폼
    @GetMapping("/login-form")
    public String loginForm() {

        return "/user/loginForm";
    }

    // 로그인
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {

        UserResponse.LoginDTO sessionUser = userService.login(reqDTO);
        System.out.println("reqDTO = " + reqDTO);
        session.setAttribute("sessionUser", sessionUser);

        return "redirect:/";
    }

    // 메인
    @GetMapping({ "/" })
    public String main() {

        return "main";
    }
}
