package com.example.messagingstompwebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.util.HtmlUtils;

public class GreetingController {

    /*
    * MessageMaaping 어노테이션은 메세지가 /hello destination으로 전송되면 greeting() 메서드가 호출되도록 한다.
    * message의 payload는 HelloMessage로 할당된다.
    * */
    @MessageMapping("/hello")
    @SendTo("topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {

        /*
        * 스레드가 1초동안 sleep 하도록 함으로써 처리 지연을 시뮬레이션 한다.
        * 이는 클라이언트가 메시지를 전송한 후 서버가 메시지를 비동기적으로 처리해야 하는 시간만큼 걸릴 수 있음을 보여주기 위한 것.
        * 클라이언트는 응답을 기다리지 않고 필요한 모든 작업을 계속할 수 있다.
        * */
        Thread.sleep(500);

        /*
        HtmlUtils.htmlEscape -> 자바에서 html 코드를 요청할 경우에 xss 예방하기 위해서 사용한다.
        return value Greeting은 @SendTo 어노테이션에 지정된 대로 /topic/greetings 구독자에게 broadcast.

         */
        return new Greeting("Hello" + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
