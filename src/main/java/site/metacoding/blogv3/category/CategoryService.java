package site.metacoding.blogv3.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 카테고리 등록
    public void save(Category category) {
        categoryRepository.save(category);
    }
}
