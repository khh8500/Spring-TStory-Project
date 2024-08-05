package site.metacoding.blogv3.post;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    // 게시글 쓰기
    @Transactional
    public Post save(Integer sessionUserId, PostRequest.SaveDTO reqDTO) {
        Post post = reqDTO.toEntity(sessionUserId, reqDTO);
        return postRepository.save(post);
    }
}
