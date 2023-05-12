package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.IIOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * 1.Request, Response 사용 꺼내고 출력하기
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }
    /**
     * 2.RequestParam을 사용하여 출력 유형1
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username")String memberName,
                                 @RequestParam("age") int memberAge){

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }
    /**
     * 3. RequestParam을 사용하여 출력 유형2
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }
    /**
     * 4. RequestParam을 제외하여 출력 유형 네이밍은 맞춰야함
     * String, int 등의 단순 타입이면 @RequestParam도 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }
    /**
     * 5.@RequestParam required를 통해 데이터의 필수 처리 여부를 체크 할 수 있음
     * default는 true임
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true)String username
            ,@RequestParam(required = false) Integer age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }
    /**
     * 6.defaultValue 를 넣어주면 값이 없는 경우 넣어줌
     * "" 빈문자 처리까지 defaultValue 처리해줌
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest")String username
            ,@RequestParam(required = false, defaultValue = "-1") int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }
    /**
     * 7.map으로 받는 것도 가능함
     * 파라미터 값이 1개가 확실하다면 Map을 사용하면 되지만 그렇지 않다면 MultiValueMap을 사용 하면 된다.
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamDefault(@RequestParam Map<String, Object> paramMap){

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }


    /**
     * @ModelAttribute를 사용하면 해당 프로퍼티를 사용하여 setter를 통해 값을 입력(바인딩) 해줌
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}",helloData);

        return "ok";
    }
    /**
     * @ModelAttribute를 생략해도 똑같이 동작 한다.
     * (argument resolver 제외)
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}",helloData);

        return "ok";
    }
}
