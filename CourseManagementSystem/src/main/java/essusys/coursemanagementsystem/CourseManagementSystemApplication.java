package essusys.coursemanagementsystem;

import essusys.coursemanagementsystem.demos.web.mapper.ClassTabMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStoppedEvent;

@SpringBootApplication
@MapperScan("essusys.coursemanagementsystem.demos.web.mapper")
public class CourseManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CourseManagementSystemApplication.class);

        // 注册事件监听器
        app.addListeners(new ApplicationListener<ContextRefreshedEvent>() {
            @Override
            public void onApplicationEvent(ContextRefreshedEvent event) {
                System.out.println("服务端正在运行...");
            }
        });

        app.addListeners(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                System.out.println("服务端停止运行");
            }
        });

        app.addListeners(new ApplicationListener<ContextStoppedEvent>() {
            @Override
            public void onApplicationEvent(ContextStoppedEvent event) {
                System.out.println("服务端停止运行");
            }
        });

        try {
            app.run(args);
        } catch (Exception e) {
            if(e.getMessage() != null){
                System.out.println("服务端运行错误: " + e.getMessage());
            }
        }
    }

}

