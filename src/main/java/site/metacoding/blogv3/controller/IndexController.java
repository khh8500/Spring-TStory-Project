package site.metacoding.blogv3.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/login-form")
    public String loginForm() {

        return "/user/loginForm";
    }

    @GetMapping("/join-form")
    public String joinForm() {

        return "/user/joinForm";
    }

    @GetMapping("/s/user")
    public String updateForm() {

        return "/user/updateForm";
    }

    @GetMapping("/user/password-reset-form")
    public String passwordResetForm() {

        return "/user/passwordResetForm";
    }

    @GetMapping("/s/category/writeForm")
    public String categoryForm() {

        return "/category/writeForm";
    }

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

    @GetMapping({ "/" })
    public String main() {

        return "main";
    }
}
