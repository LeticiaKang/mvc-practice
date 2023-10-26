package org.example.mvc.controller;

import org.example.mvc.model.User;
import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class userCreateController implements Controller{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /**
         * (POST) 요청 받은 userId와 name을 가지고 User를 생성해 저장한다.
         * 클라이언트쪽으로 users 응답을 보낸다. (users 경로로 다시 한 번 요청해줘)
         * (GET) 클라이언트에서 다시 users로 요청을 보낸다.
         * DispatchServlet이 다시 이 요청을 받아서 GET요청의 users에 대한 Controller Mapping을 한다.
         */
        UserRepository.save(new User(request.getParameter("userId"), request.getParameter("name")));
        return "redirect:/users";
    }
}
