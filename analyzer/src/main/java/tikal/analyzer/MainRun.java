package tikal.analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = { "tikal.analyzer" } )
public class MainRun 
{
	
    public static void main(String[] args) {
        SpringApplication.run(MainRun.class, args);
    }

}

