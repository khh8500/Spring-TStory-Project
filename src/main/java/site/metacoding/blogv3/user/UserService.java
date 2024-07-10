package site.metacoding.blogv3.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

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

        User user = userRepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow();

        return new UserResponse.LoginDTO(user);
    }

    // 기존 비밀번호 확인
    @Transactional
    public void checkPassword(UserResponse.LoginDTO sessionUser, String password) {

        if (sessionUser == null) {
            throw new RuntimeException("세션에 사용자 정보가 없습니다");
        }

        // 비밀번호 확인
        if (!sessionUser.getPassword().equals(password)) {
            throw new RuntimeException("유효한 비밀번호가 아닙니다");
        }
    }

    // 비밀번호 변경
    @Transactional
    public UserResponse.LoginDTO changePassword(UserResponse.LoginDTO sessionUser, String password, String newPassword) {

        if (sessionUser == null) {
            throw new RuntimeException("세션에 사용자 정보가 없습니다");
        }

        User user = userRepository.findByUsernameAndPassword(sessionUser.getUsername(), password)
                .orElseThrow();

        // 비밀번호 변경
        user.changePassword(newPassword);
        userRepository.save(user);

        return new UserResponse.LoginDTO(user);
    }
}
