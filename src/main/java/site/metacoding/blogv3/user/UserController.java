package site.metacoding.blogv3.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


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

        return "redirect:/login-form";
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

    // 기존 비밀번호 확인
    @PostMapping("/check-password")
    @ResponseBody
    public Map<String, Object> checkPassword(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        UserResponse.LoginDTO sessionUser = (UserResponse.LoginDTO) session.getAttribute("sessionUser");

        try {
            String password = request.get("password");
            userService.checkPassword(sessionUser, password);
            response.put("success", true);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        System.out.println("Response: " + response); // 서버 로그에 응답 출력
        return response;
    }

    // 비밀번호 변경
    @PostMapping("/change-password")
    @ResponseBody
    public Map<String, Object> changePassword(@RequestBody UserRequest.ChangePasswordDTO reqDTO) {
        Map<String, Object> response = new HashMap<>();
        UserResponse.LoginDTO sessionUser = (UserResponse.LoginDTO) session.getAttribute("sessionUser");

        try {
            userService.changePassword(sessionUser, reqDTO.getPassword(), reqDTO.getNewPassword());
            System.out.println("reqDTO = " + reqDTO);
            response.put("success", true);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
}
