package org.example.mvc;

import org.example.mvc.controller.*;
import org.example.mvc.view.HandlerMapping;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping implements HandlerMapping {
//    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private Map<HandlerKey, Controller> mappings = new HashMap<>();

    void init() {
        mappings.put(new HandlerKey(RequestMethod.GET,  "/"), new HomeController());
        mappings.put(new HandlerKey(RequestMethod.GET,  "/users"), new userListController()); // get일 때, post일 때 구분이 되어야 한다.
        mappings.put(new HandlerKey(RequestMethod.POST,  "/users"), new userCreateController());
        // GET요청의 user/form 이 들어오면 => 바로 user/form으로 이동함(다른 것들은 처리가 필요함)
        mappings.put(new HandlerKey(RequestMethod.GET,  "/user/form"), new ForwardController("/user/form"));

    }

    public Controller findHandler(HandlerKey handlerKey) {
        return mappings.get(handlerKey);
        // form.jsp에서 (POST,"/users") 보냄 => userCreateController() 반환
    }

}
