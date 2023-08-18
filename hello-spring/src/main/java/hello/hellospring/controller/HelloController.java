package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // GetMapping 애너테이션의 사용은, http 메서드 get, post, put, delete등의 메서드를 사용하면서 엔드포인트 이름으로 /hello를 사용하겠다는 의미이다.
    @GetMapping("hello")
    public String hello(Model model){
        // key 값으로 data, value 값으로 hello!를 사용하는 것이다.
        model.addAttribute("data", "hello!");
        // return "hello";를 사용하면 hello.html 파일을 찾아간다.
        return "hello";
    }

    @GetMapping("hello-mvc")
    // 웹 페이지에서 parameter를 받기 위해서는 @RequiestParam 애너테이션을 사용한다. 그에 따른 타입은 String 타입, 변수명은 name
    // 그리고 model 모듈을 사용하기 위해서 Model model을 파라미터로 제공하였다.
    // key 값 "name"을 사용하여서 웹에서 받은 parameter의 값 name을 아래 model.addAttribute를 통해 해당 name에 데이터를 제공하는 시스템으로 이루어져 있다.

    // http://localhost:8080/hello-mvc?name=spring
    // @RequestParam 메서드에 파라미터로 value = " 사용자값 " 을 넣어주면 localhost:port/endpoint?사용자값 = 넘겨줄데이터입력 이런 형식을 갖추고 있다.
    // ctrl + p 를 누르면 필요한 파라미터 정보가 나온다.
    // @RequestParam의 required 파라미터는 default가 True라서 굳이 적을 필요가 없다.
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-api")
    // @ResponseBody 애너테이션을 작성하면 return의 값이 html의 형식으로 그대로 전달된다.
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
