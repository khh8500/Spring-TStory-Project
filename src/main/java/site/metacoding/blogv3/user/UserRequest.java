package site.metacoding.blogv3.user;

import lombok.Data;

public class UserRequest {

    // 회원가입
    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }
    }

    // 로그인
    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    // 비밀번호 변경
    @Data
    public static class ChangePasswordDTO {
        private String password;
        private String newPassword;
    }
}
