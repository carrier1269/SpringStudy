package hello.hellospring.domain;

import jakarta.persistence.*;

// jpa는 객체와 ORM으로써, Object Relational Mapping -> 객체와 데이터 테이블을 애너테이션을 통해 mapping한다는 뜻이다.
// jpa가 관리하는 Entity라는 뜻
@Entity
public class Member {
    // DB에 데이터를 넣을때 자동으로 ID를 지정해주는 것을 확인할 수 있었는데, 이것을 GenerationType.IDENTITY라고 한다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 아래와 같이 작성하면 DB의 username컬럼의 내용들을 name 변수에 mapping한다는 뜻이다.
    // 이것이 애너테이션을 통해서 mapping한다는 내용이라 봐도 무방하다.
//    @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
