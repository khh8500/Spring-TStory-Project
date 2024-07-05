package site.metacoding.blogv3.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public UserResponse.JoinDTO join(UserRequest.JoinDTO reqDTO) {

        User user = userRepository.save(reqDTO.toEntity());
        System.out.println("reqDTO = " + reqDTO);

        return new UserResponse.JoinDTO(user);
    }

    // 로그인
    public UserResponse.LoginDTO login(UserRequest.LoginDTO reqDTO) {

        userRepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow();

        return new UserResponse.LoginDTO();
    }
}
