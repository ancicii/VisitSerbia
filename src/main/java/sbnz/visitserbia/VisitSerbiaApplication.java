package sbnz.visitserbia;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.ApplicationScope;

@SpringBootApplication
public class VisitSerbiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisitSerbiaApplication.class, args);
    }

    @Bean
    @ApplicationScope
    public KieContainer kieContainer() {
        return KieServices.Factory.get().getKieClasspathContainer();
    }

}
