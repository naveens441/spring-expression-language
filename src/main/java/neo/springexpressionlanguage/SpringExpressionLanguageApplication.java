package neo.springexpressionlanguage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@SpringBootApplication

public class SpringExpressionLanguageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExpressionLanguageApplication.class, args);
	}

}
@RestController
class Controller {

    @GetMapping("/")
    public String greet(){
        return "hello Neo!";
    }

}
@Component("uuid")
class UuidService {
	public String buildUuid() {
		return UUID.randomUUID().toString();
	}
}
@Component
class Bar{
	private final Foo foo;
	private final Log log = LogFactory.getLog(getClass());
	Bar(Foo foo,@Value("#{uuid.buildUuid()}") String uuid,
			@Value("#{ 2>1}") boolean fact){
		this.foo=foo;
		this.log.info("uuid :"+uuid);
		log.info("fact :"+fact);
	}
}
@Component()
class Foo{
}
