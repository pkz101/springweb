package com.gedi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan // 扫描Servlet组件
@SpringBootApplication
public class ZpkWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZpkWebManagementApplication.class, args);
    }

}
