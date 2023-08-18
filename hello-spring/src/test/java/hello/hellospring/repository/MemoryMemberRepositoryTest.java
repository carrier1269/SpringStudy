package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        // .get() 메서드는 Optional 반환 객체의 .get()메서드의다. 한마디로 Optional 메서드이다.
        Member result = repository.findById(member.getId()).get();
        // Member result = repository.findById(1L).get();
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
//        Assertions.assertEquals(member, result); // 둘이 값이 다르면 에러를 반환한다.
//        System.out.println("result = " + (result == member));
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        org.assertj.core.api.Assertions.assertThat(member1).isEqualTo(result);

//        List<Member> result = repository.findAll();
//
//        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
    }

}
