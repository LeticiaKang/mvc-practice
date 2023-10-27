package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HandlerKey;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// Tomcat이 해당 파일을 실행하려면 Servlet이어야 하기 때문에 Servlet을 상속받는다.
@WebServlet("/") // 요청이 오는 모든 경로에 대해 DispatcherServlet이 실행된다.
public class DispatcherSevlet extends HttpServlet {
    /**
     * Tomcat이 HttpServlet을 싱글톤 객체 1개로 만드는데,
     * 그때 init메서드가 호출된다. 동시에 rmhm(RequestMappingHandlerMapping)이 초기화된다.
     */

    private RequestMappingHandlerMapping rmhm;

    private static final Logger log = LoggerFactory.getLogger(DispatcherSevlet.class);

    private List<ViewResolver> viewResolvers;

    @Override
    public void init() throws ServletException {
        rmhm = new RequestMappingHandlerMapping();
        rmhm.init();

        viewResolvers = Collections.singletonList(new JspViewResolver()); //초기화
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 요청이 들어오면, 해당 객체에게 작업을 위임한다.
         * 요청 url에 대한 처리 핸들러를 얻어서 handler(Controller)에 저장한다.(mappings.get(urlPath)을 통해서 urlPath를 반환하는것)
         * handler에게 request, response를 보내 다시 위임한다.
         */
        log.info("=====[DispatcherSevlet] service started====="); // 연결마다 출력됨을 확인함

        /**
         * form.jsp => handler는 userCreateController()가 된다.
         * userCreateController의 handleRequest(request, response)의 반환값은 "redirect:/users"
         * viewName은 "redirect:/users" 가 된다.
         * redirect:/users는 request.getRequestDispatcher(viewName)에 맞지 않기 때문에 ViewResolver로 나눠줘야 한다.
         */
        try {
            System.out.println("================Dispatcher service 정상 작동=================");

            Controller handler = rmhm.findHandler(new HandlerKey(RequestMethod.valueOf(request.getMethod()), request.getRequestURI()));
            String viewName = handler.handleRequest(request, response); //"redirect:/users"

            for (ViewResolver viewResolver : viewResolvers) {
                View view = viewResolver.resolveView(viewName);
                view.render(new HashMap<>(), request, response);
            }

//            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
//            requestDispatcher.forward(request,response);

        } catch (Exception e) {
            log.error("====exception occurred: [{}]====", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
