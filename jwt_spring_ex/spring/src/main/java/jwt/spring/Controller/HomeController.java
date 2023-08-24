package jwt.spring.Controller;

import jwt.spring.Service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
@Controller
public class HomeController {
//    private final AppleService appleService;
    private final KakaoService kakaoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model) {
//        model.addAttribute("appleUrl", appleService.getAppleLogin());
        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());

        return "index";
    }
}
