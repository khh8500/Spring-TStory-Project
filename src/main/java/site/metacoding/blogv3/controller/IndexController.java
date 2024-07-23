package site.metacoding.blogv3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/user/password-reset-form")
    public String passwordResetForm() {

        return "/user/passwordResetForm";
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

}
