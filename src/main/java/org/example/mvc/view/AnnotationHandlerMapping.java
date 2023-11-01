package org.example.mvc.view;

import javassist.ClassMap;
import org.example.mvc.controller.HandlerKey;

import java.util.HashMap;
import java.util.Map;

public class AnnotationHandlerMapping implements HandlerMapping{
    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
