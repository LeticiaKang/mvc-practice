package org.example;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebApplicationServer {
    private static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);

    public static void main(String[] args) throws Exception {
        /**
         *  코드 설명
         *  해당 tomcat에 8080 port를 입력했을 때, webapps라는 directory를 Root directory를 보고
         *  그 하위 파일을 읽어서 실행함
         */
        String webAppDirLocation = "webapps/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        tomcat.addWebapp("/", new File(webAppDirLocation).getAbsolutePath());
        log.info("configuring app with basedir: {}", new File("./" + webAppDirLocation).getAbsolutePath());

        tomcat.start();
        System.out.println("================Tomcat 정상 작동=================");
        tomcat.getServer().await();
    }
}