package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.*;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

//    @Autowired
    // JdbcTemplate를 작성할 때는 Injection으로 DataSource를 주면 된다.
    // DataSource는 Bean에 등록하여야 하며, 생성자가 하나인 경우 @AutoWired를 생략할 수 있다.
    // JdbcTemplate를 이해하는 방법은, 예를 들어서
    // this.JdbcTemplate.queryForObject("select count(*) from t_actor where first_name = ?", Integer.class, "Joe"); 인 경우
    // t_actor에서 이름이 Joe인 경우를 세서 반환하는 것을 확인할 수 있다.
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
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

//    private String findany() {
//        List<String> elements = Arrays.asList("a", "a1", "b", "b1", "c", "c1");
//        Optional<String> firstElement = elements.stream()
//                .filter(s -> s.startsWith("b")).findFirst(); // findFirst로 작성할 경우 b로 시작하는 원소중에서 항상 배열의 첫 번째 원소를 반환한다.
////                .filter(s -> s.startsWith("b")).findAny(); # findAny로 작성할 경우 b로 시작하는 원소중에서 아무거나 반환을 한다. 순서 상관 x
//
////        System.out.println("b로 시작하는 첫 번째 원소는 : " + firstElement.get());
//        return firstElement.get();
//    }

}
