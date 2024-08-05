package site.metacoding.blogv3.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.metacoding.blogv3.user.User;
import site.metacoding.blogv3.user.UserRepository;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    // 카테고리 등록
    public void save(CategoryRequest.SaveDTO reqDTO, Integer sessionUserId) {

        System.out.println("sessionUserId11111 = " + sessionUserId);
        User sessionUser = userRepository.findById(sessionUserId)
                        .orElseThrow(() -> new RuntimeException("사용자 정보가 없습니다"));
        System.out.println("sessionUserId22222 = " + sessionUserId);
        Category category = reqDTO.toEntity(sessionUserId);

        System.out.println("sessionUserId33333 = " + sessionUserId);
        categoryRepository.save(category);
    }
}
