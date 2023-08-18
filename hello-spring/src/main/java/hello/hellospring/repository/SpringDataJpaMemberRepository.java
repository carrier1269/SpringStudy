package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface가 interface를 상속받을때는 extends를 사용한다.
// interface는 다중 상속이 가능하다.
// springdatajpa가 jparepository를 받고있으면 springdatajpa가 구현체를 자동으로 만들어서 스프링빈에 자동으로 등록해준다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
