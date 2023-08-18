package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
//    @Test
//    void join() {
//    }
    // 테스트는 과감하게 메서드이름을 한글로 작성해도 문제가 되지 않는다.
    @Test
    void 회원가입() {
        //given -> 무언가가 주어졌는데
        Member member = new Member(); // member 클래스에 대한 객체를 생성하고
        member.setName("hello"); // 새로 들어온 멤버의 이름을 hello라고 setter 메서드를 통해 설정합니다.

        //when -> 이것을 실행했을 때
//        System.out.printf("반환 값은 : " + memberService.join(member));
        Long saveId = memberService.join(member); // memberService 클래스의 join 메서드를 사용하여 방금 만든 member 정보를 동록하고 member객체를 반환받은 값을 saveId에 저장한다.

//        memberService.memberlist(); // store hashmap을 반환하면 {1=hello.hellospring.domain.Member@1242155}값을 얻을 수 있다.
        // store hashmap 구조가 <Long, Member>타입을 이루고 있기 때문에, Long 자료형의 id값 1을 반환하고 그의 value 로 Member 타입의 값을 가지고 있는 member을 반환하는 것을 확인할 수 있다.

        //then -> 결과가 이게 나와야해
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }


    @Test
    public void 종복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            // 에러메시지가 발생했는데 에러메시지에 대해서 처리를 하지 않으면 에러가 뜬다.
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }

        // when

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}