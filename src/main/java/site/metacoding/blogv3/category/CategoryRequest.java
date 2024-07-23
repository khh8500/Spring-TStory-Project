package site.metacoding.blogv3.category;

import lombok.Data;

public class CategoryRequest {

    @Data
    public static class SaveDTO {
        private String categoryName;

        public Category toEntity() {
            return new Category(categoryName);
        }
    }
}
