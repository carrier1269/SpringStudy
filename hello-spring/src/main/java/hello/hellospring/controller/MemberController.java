package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    // 다른 멤버 컨트롤러들이 MemberService를 갖다 쓸수 있지만 공유가 안되므로 아까와 같이 DI를 통해 문제를 해결하면 된다.
    private final MemberService memberService;

    // @AutoWired를 쓰면 스프링 컨테이너가 가져다 쓸때 멤버 컨트롤러를 연결시켜준다.
    // Service와 Repository에 @Service, @Repository라고 애너테이션을 설정 해주어야지
    // 스프링 컨테이너가 memberService, memberRepository를 불러오기 때문에
    // memberController에서 memberService를 @AutoWired를 통해 불러올 때 문제가 되지 않는다.

    // Controller, Service, Repository를 엮어주는 애너테이션
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        // redirect:/를 쓰면 /로 돌아간다는 뜻이다.
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
