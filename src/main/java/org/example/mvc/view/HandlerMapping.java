package org.example.mvc.view;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HandlerKey;

public interface HandlerMapping {

    Object findHandler(HandlerKey handlerKey);
}
