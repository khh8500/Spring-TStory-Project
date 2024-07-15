package site.metacoding.blogv3.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import site.metacoding.blogv3._core.utils.ApiUtil;

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

    // 로그아웃
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    // 메인
    @GetMapping({"/"})
    public String main() {

        return "main";
    }

    // 유저정보 업데이트 폼
    @GetMapping("/s/user")
    public String updateForm() {

        return "/user/updateForm";
    }

    // 기존 비밀번호 확인
    @PostMapping(value = "/check-password", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> checkPassword(@RequestBody Map<String, String> request) {
        try {
            UserResponse.LoginDTO sessionUser = (UserResponse.LoginDTO) session.getAttribute("sessionUser");
            String password = request.get("password");
            userService.checkPassword(sessionUser, password);
            return ResponseEntity.ok(new ApiUtil<>("비밀번호 확인되었습니다"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiUtil<>(400, e.getMessage()));
        }
    }

    // 비밀번호 변경
    @PostMapping(value = "/change-password", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ApiUtil<String>> changePassword(@RequestBody UserRequest.ChangePasswordDTO reqDTO) {
        try {
            UserResponse.LoginDTO sessionUser = (UserResponse.LoginDTO) session.getAttribute("sessionUser");
            userService.changePassword(sessionUser, reqDTO.getPassword(), reqDTO.getNewPassword());
            return ResponseEntity.ok(new ApiUtil<>("비밀번호 변경되었습니다"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiUtil<>(400, e.getMessage()));
        }
    }
}
