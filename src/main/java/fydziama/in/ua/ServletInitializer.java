package fydziama.in.ua;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAutoConfiguration
@SpringBootApplication
@EnableAspectJAutoProxy // включаем использование AspectJ
@ComponentScan(basePackages = {"fydziama.in.ua"})
public class ServletInitializer extends SpringBootServletInitializer {

}
