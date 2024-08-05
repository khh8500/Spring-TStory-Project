package site.metacoding.blogv3.post;

import lombok.Data;
import site.metacoding.blogv3.category.Category;
import site.metacoding.blogv3.user.User;

public class PostRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String content;
        private String thumbnail;
        private Integer categoryId;

        public Post toEntity(Integer sessionUserId, SaveDTO reqDTO) {
            User user = new User();
            user.setId(sessionUserId);
            Category category = new Category();
            category.setId(reqDTO.getCategoryId());

            return Post.builder()
                    .title(this.title)
                    .content(this.content)
                    .thumbnail(this.thumbnail)
                    .user(user)
                    .category(category)
                    .build();
        }
    }

}
