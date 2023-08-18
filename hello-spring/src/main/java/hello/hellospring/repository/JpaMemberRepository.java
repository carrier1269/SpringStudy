package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // jpa는 entitymanager라는 것으로 모든것을 동작한다.
    // properties에서 jpa 라이브러리를 import하면 내부적으로 다 엮어서 entitymanager라는 것을 자동으로 생성해주기 때문에,
    // EntityManger를 받아 쓰기만 하면 된다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // persist, 영구저장한다라는 뜻이다.
        // 이렇게 하면 jpa가 insert query를 만들어서 id셋까지 만들어서 DB에 자동으로 저장해준다.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =  em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();

    }

//    @Override
//    public void listview() {
//
//    }
//
//    @Override
//    public void clearStore() {
//
//    }
}
