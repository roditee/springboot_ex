package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

//    @ResponseBody 를 사용
//    HTTP의 BODY에 문자 내용을 직접 반환
//    viewResolver 대신에 HttpMessageConverter 가 동작
//    기본 문자처리: StringHttpMessageConverter
//    기본 객체처리: MappingJackson2HttpMessageConverter
//    byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음

    @GetMapping("hello-string")
    @ResponseBody // body 부분에 return에 해당하는 내용 직접 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
        // @ResponseBody 를 사용하면 뷰 리졸버( viewResolver )를 사용하지 않음
        //대신에 HTTP의 BODY에 문자 내용을 직접 반환(HTML BODY TAG를 말하는 것이 아님)
        // 실행 후 [페이지소스보기] -> HTML 전체 소스가 아닌 return에 해당하는 내용만 나옴
        // view를 조작하는 것이 아닌, 그냥 그대로를 출력
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // @ResponseBody 를 사용하고, '객체'를 반환하면 객체가 'JSON' 방식으로 변환됨
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}