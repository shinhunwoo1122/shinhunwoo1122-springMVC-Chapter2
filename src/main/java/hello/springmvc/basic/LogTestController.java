package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //lombok에서 자동으로 주입 시켜줌
@RestController
public class LogTestController {
    //두가지 형태로 직접 지정 할 수 있고 slf4j 선택 해야 함 log의 종류는 많고 slf4j interface를 사용하여 다른 log들을 구체화하여 다형성으로 사용함
    //private final Logger log = LoggerFactory.getLogger(LoggerFactory.class);
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name ="Spring";

        //전부 출력되기 떄문에 운영서버에는 log 폭탄 맞을 수 있음.
        System.out.println("name = " + name);

        //{} 안에 name 치환되는것 여러개 넣을 수도 있음 레벨별로 설정 할수 있음 default info부터 "+" 사용하면 연산작업을 진행하여 메모리 소모
        //application.properties 안에 logging.level을 통해 trace, debug 노출 가능 개발서버 debug 로컬서버 trace 운영은 info
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
