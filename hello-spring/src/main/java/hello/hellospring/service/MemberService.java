package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {
    // 항상 new를 통해 새로운 객체의 MemoryMemberRepository를 사용하게 되면 같은 메모리를 사용하지 못하게 되기 때문에,
    // 이를 방지하기 위해서 인터페이스 객체 파라미터에 직접적으로 구현체를 제공하고 new를 삭제하게 되는데,
    // service 코드에서 새로운 객체를 생성하는 것을 삭제하였기 때문에 실질적으로 내부에서 동작할 때는, 새로운 객체를 생성하는 것이 아닌,
    // 메모리를 서로 공유하기 때문에 같은 메모리 사용이 되고, 외부에서 동작테스트를 할때는 새로운 객체를 생성하여 그 객체를 계속 사용하기 때문에 문제가 되지 않는다.
    // 실행시킬때마다 계속 내부적으로 새로운 객체가 생성되어 같은 메모리를 사용하지 못하는 문제를 해결한것.
    // 이를 DI, dependency injection이라고 한다.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 회원가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복회원 제거
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        // ifPresent 는 Optional을 쓸때 값이 존재하면 동작하는 메서드이다.
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    // 전체 회원 조회
     public List<Member> findMembers() {
        return memberRepository.findAll();
     }

     public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
     }

//     public void memberlist() {
//        memberRepository.listview();
//     }
//
//     public void clearMember () {
//        memberRepository.clearStore();
//     }
}
