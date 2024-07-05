package site.metacoding.blogv3.user;

import lombok.Data;

public class UserResponse {

    // 회원가입
    @Data
    public static class JoinDTO {
        private Integer id;
        private String username;
        private String password;
        private String email;

        public JoinDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.email = user.getEmail();
        }
    }

    // 로그인
    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }
}
