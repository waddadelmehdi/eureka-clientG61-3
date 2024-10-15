package ma.emsi.Eureka_clientG61_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class EurekaClientG613Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientG613Application.class, args);
	}

}
@RestController 
class AggregatorController {

    @Autowired
    private GreetingClient greetingClient; 

    @Autowired 
    private DateClient dateClient; 

    @GetMapping("/aggregate")
    public String getAggregateData() { 
        String greeting = greetingClient.getGreeting(); 
        String date = dateClient.getCurrentDate(); 
        return greeting + " | " + date; 
    }
}

@FeignClient(name = "Eureka-clientG61-1") 
interface GreetingClient {
    @GetMapping("/greeting") 
    String getGreeting(); 
}

@FeignClient(name = "Eureka-clientG61-2")
interface DateClient {
    @GetMapping("/date") 
    String getCurrentDate(); 
}
