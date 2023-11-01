package org.example.mvc.view;

import org.example.mvc.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {

    boolean suppeorts(Object handler);
    // 전달된 handler를 지원하는지 확인 -> 전달된 handler가 Controller의 구현체이면 return 해줌


    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)  throws Exception;
    // handler가 지원되면 -> request, response를 받아서 modelandview를 리턴 받음

}
