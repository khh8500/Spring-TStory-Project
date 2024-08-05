package site.metacoding.blogv3.category;

import lombok.Data;
import site.metacoding.blogv3.user.User;

public class CategoryRequest {

    @Data
    public static class SaveDTO {
        private String categoryName;

        public Category toEntity(Integer sessionUserId) {
            User user = new User();
            user.setId(sessionUserId);
            return Category.builder()
                    .categoryName(this.categoryName)
                    .user(user) // 사용자 정보를 설정
                    .build();
        }

    }
}
