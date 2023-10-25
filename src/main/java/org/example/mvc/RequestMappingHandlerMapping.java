package org.example.mvc;

import org.example.mvc.controller.HomeController;
import org.example.mvc.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
//    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private Map<String, Controller> mappings = new HashMap<>();

    void init() {
        mappings.put("/", new HomeController());

//        mappings.put(new HandlerKey("/user/form", RequestMethod.GET), new ForwardController("/user/form"));
//        mappings.put(new HandlerKey("/users", RequestMethod.GET), new UserListController());
//        mappings.put(new HandlerKey("/users", RequestMethod.POST), new UserCreateController());
//
//        mappings.keySet().forEach(path ->
//                logger.info("url path: [{}], controller: [{}]", path, mappings.get(path).getClass()));
    }


    public Controller findHandler(String urlPath) {
        return mappings.get(urlPath);
    }

}
