package jwt.jwtspring.DTO;

import jakarta.validation.constraints.NotBlank;
import jwt.jwtspring.Member.User;
import jwt.jwtspring.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// DTO를 제작하는 이유는 Member 정보에 대해서
// 클라이언트에게 정보를 제공할 때, 보여줄수 있는 정보가 있고 아닌 정보가 있기 때문에,
// 유저에게 보여줘도 되는 정보에 대해서는 DTO를 통해서 controller와 데이터를 송수신할수 있도록 제작하고,
// 내부적으로는 Member을 관리하는 repository를 통해서 관리한다.

@Getter
@Setter
// Lombok 라이브러리 애너테이션 @Getter @Setter을 클래스에 명시해주면, 모든 필드에 생성자 (캡슐화)가 자동으로 생성된다.
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 제작한다.
// @AllArgsConstructor은 모든 필드 값을 파라미터로 받는 생성자를 만든다.
/**
 * 예를 들어서 JoinRequest안에 필드값으로 value를 받는다 하면
 * JoinRequest(Value value)와 같은 생성자를 제작하게 될텐데,
 * 이를 사용하는 애너테이션이 @AllArgsConstructor이고,
 * 이를 사용하지 않는 것이 @NoArsConstructor이다.
 */

public class JoinRequest {
    /**
     * @NotNull 은 ""이나 " "은 허용하지만 Null값만 허용하지 않습니다. Null일 경우 message를 담아 반환합니다.
     * @NotEmpty 는 Null이나 ""은 허용하지 않지만 " "은 허용합니다.
     * @NotBlank 는 Null이나 "", " " 전부 허용하지 않습니다.
     */
    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String loginId;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;
    private String passwordCheck;

    @NotBlank(message = "닉네임이 비어있습니다.")
    private String nickname;

    // 비밀번호 암호화 X
    public User toEnity() {
        return User.builder()
                .loginId(this.loginId)
                .password(this.password)
                .nickname(this.nickname)
                .role(UserRole.User)
                .build();
    }

    // 비밀번호 암호화
    public User toEntity(String encodedPassword) {
        return User.builder()
                .loginId(this.loginId)
                .password(encodedPassword)
                .nickname(this.nickname)
                .role(UserRole.User)
                .build();
    }
}
