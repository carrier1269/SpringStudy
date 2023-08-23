package jwt.jwtspring.Service;

import jakarta.transaction.Transactional;
import jwt.jwtspring.DTO.JoinRequest;
import jwt.jwtspring.DTO.LoginRequest;
import jwt.jwtspring.Member.User;
import jwt.jwtspring.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean checkLoginIdDuplicate(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    // User클래스에서 설정한 toEntity()메서드, 암호화되지 않은 엔티티 저장
    public void join(JoinRequest req) {
        userRepository.save(req.toEnity());
    }

    public void join2(JoinRequest req) {
        // 블로그 예제에서 나온 encoder 메소드를 몰라서 Base64 라이브러리를 사용했음.
        // 파라미터를 통해 들어온 값(JoinRequest 클래스에 @Getter @Setter 메서드를 사용해서 get메소드를 그냥 사용가능)
        // 값 = 비밀번호 --> 를 Byte로 변환하고 base64로 인코딩하여 문자열로 변환하고 이를 toEntity 메소드를 통해
        // 엔티티로 변환시킨 것을 userRepository에 저장한다.
        userRepository.save(req.toEntity(Base64.getEncoder().encodeToString(req.getPassword().getBytes())));
    }

    public User login(LoginRequest req) {
        Optional<User> optionalUser = userRepository.findByLoginId(req.getLoginId());

        if(optionalUser.isEmpty()) {
            return null;
        }

        // Optional 타입으로 감싸줬기 때문에 id가 null일 경우에도 동작을 한다.
        // Optional 타입을 사용했기 때문에 값을 얻으려면 get 메소드를 사용해야 한다.
        User user = optionalUser.get();

        if(!user.getPassword().equals(req.getPassword())) {
            return null;
        }

        return user;
    }

    public User getLoginUserById(Long userId) {
        if(userId == null) return null;

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    public User getLoginUserByLoginId(String loginId) {
        if(loginId == null) return null;

        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }
}
