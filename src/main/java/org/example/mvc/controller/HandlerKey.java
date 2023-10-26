package org.example.mvc.controller;

import java.util.Objects;

public class HandlerKey {

    private String uriPath;
    private RequestMethod requestMethod;

    public HandlerKey(RequestMethod requestMethod, String uriPath) {
            this.uriPath = uriPath;
            this.requestMethod = requestMethod;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandlerKey that = (HandlerKey) o;
        return Objects.equals(uriPath, that.uriPath) && requestMethod == that.requestMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uriPath, requestMethod);
    }
}
