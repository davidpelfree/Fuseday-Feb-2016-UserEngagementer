package tikal.analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "tikal.analyzer" } )
public class MainRun 
{
	
    public static void main(String[] args) {
        SpringApplication.run(MainRun.class, args);
    }

}

