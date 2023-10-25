package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Tomcat이 해당 파일을 실행하려면 Servlet이어야 하기 때문에 Servlet을 상속받는다.
@WebServlet("/") // 요청이 오는 모든 경로에 대해 DispatcherServlet이 실행된다.
public class DispatcherSevlet extends HttpServlet {
    /**
     * Tomcat이 HttpServlet을 싱글톤 객체 1개로 만드는데,
     * 그때 init메서드가 호출된다. 동시에 rmhm(RequestMappingHandlerMapping)이 초기화된다.
     */

    private RequestMappingHandlerMapping rmhm;

    private static final Logger log = LoggerFactory.getLogger(DispatcherSevlet.class);

    @Override
    public void init() throws ServletException {
        rmhm = new RequestMappingHandlerMapping();
        rmhm.init();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 요청이 들어오면, 해당 객체에게 작업을 위임한다.
         * 요청 url에 대한 처리 핸들러를 얻어서 handler(Controller)에 저장한다.(mappings.get(urlPath)을 통해서 urlPath를 반환하는것)
         * handler에게 request, response를 보내 다시 위임한다.
         */
        log.info("[DispatcherSevlet] service started"); // 연결마다 출력됨을 확인함

        try {
            Controller handler = rmhm.findHandler(request.getRequestURI());
            String viewName = handler.handleRequest(request, response);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
            requestDispatcher.forward(request,response);

        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
